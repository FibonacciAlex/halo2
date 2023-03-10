package run.halo.app.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.Unstructured;
import run.halo.app.infra.properties.HaloProperties;
import run.halo.app.infra.utils.YamlUnstructuredLoader;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Extension resources initializer.</p>
 * <p>Check whether {@link HaloProperties#getInitialExtensionLocations()} is configured
 * When the system ready, and load resources according to it to creates {@link Unstructured}</p>
 *
 * @author guqing
 * @since 2.0.0
 */
@Slf4j
@Component
public class ExtensionResourceInitializer {

    public static final Set<String> REQUIRED_EXTENSION_LOCATIONS =
        Set.of("classpath:/extensions/*.yaml", "classpath:/extensions/*.yml");
    private final HaloProperties haloProperties;
    private final ReactiveExtensionClient extensionClient;

    public ExtensionResourceInitializer(HaloProperties haloProperties,
        ReactiveExtensionClient extensionClient) {
        this.haloProperties = haloProperties;
        this.extensionClient = extensionClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public Mono<Void> initialize(ApplicationReadyEvent readyEvent) {
        var locations = new HashSet<String>();
        if (!haloProperties.isRequiredExtensionDisabled()) {
            locations.addAll(REQUIRED_EXTENSION_LOCATIONS);
        }
        if (haloProperties.getInitialExtensionLocations() != null) {
            locations.addAll(haloProperties.getInitialExtensionLocations());
        }
        if (CollectionUtils.isEmpty(locations)) {
            return Mono.empty();
        }

        return Flux.fromIterable(locations)
            .doOnNext(location ->
                log.debug("Trying to initialize extension resources from location: {}", location))
            .map(this::listResources)
            .distinct()
            .flatMapIterable(resources -> resources)
            .doOnNext(resource -> log.debug("Initializing extension resource from location: {}",
                resource))
            .map(resource -> new YamlUnstructuredLoader(resource).load())
            .flatMapIterable(extensions -> extensions)
            .doOnNext(extension -> {
                if (log.isDebugEnabled()) {
                    log.debug("Initializing extension resource: {}/{}",
                        extension.groupVersionKind(), extension.getMetadata().getName());
                }
            })
            .flatMap(this::createOrUpdate)
            .doOnNext(extension -> {
                if (log.isDebugEnabled()) {
                    log.debug("Initialized extension resource: {}/{}", extension.groupVersionKind(),
                        extension.getMetadata().getName());
                }
            })
            .then();
    }

    private Mono<Unstructured> createOrUpdate(Unstructured extension) {
        return Mono.just(extension)
            .flatMap(ext -> extensionClient.fetch(extension.groupVersionKind(),
                extension.getMetadata().getName()))
            .flatMap(existingExt -> {
                extension.getMetadata().setVersion(existingExt.getMetadata().getVersion());
                return extensionClient.update(extension);
            })
            .switchIfEmpty(Mono.defer(() -> extensionClient.create(extension)))
            .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100))
                .filter(t -> t instanceof OptimisticLockingFailureException))
            .onErrorContinue(OptimisticLockingFailureException.class, (throwable, o) -> {
                log.warn("Failed to create or update extension resource: {}/{} due to modification "
                        + "conflict",
                    extension.groupVersionKind(), extension.getMetadata().getName());
            });
    }

    private List<Resource> listResources(String location) {
        var resolver = new PathMatchingResourcePatternResolver();
        try {
            return List.of(resolver.getResources(location));
        } catch (IOException ie) {
            throw new IllegalArgumentException("Invalid extension location: " + location, ie);
        }
    }

}
