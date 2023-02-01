package run.halo.app.core.extension.gc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import run.halo.app.core.extension.ExtensionClient;
import run.halo.app.core.extension.controller.*;
import run.halo.app.core.extension.store.ExtensionStoreClient;
import run.halo.app.core.extension.Extension;
import run.halo.app.core.extension.ExtensionConverter;
import run.halo.app.core.extension.SchemeManager;
import run.halo.app.core.extension.controller.*;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;

@Slf4j
@Component
class GcReconciler implements Reconciler<GcRequest> {

    private final ExtensionClient client;

    private final ExtensionStoreClient storeClient;

    private final ExtensionConverter converter;

    private final SchemeManager schemeManager;

    GcReconciler(ExtensionClient client, ExtensionStoreClient storeClient,
        ExtensionConverter converter, SchemeManager schemeManager) {
        this.client = client;
        this.storeClient = storeClient;
        this.converter = converter;
        this.schemeManager = schemeManager;
    }


    @Override
    public Result reconcile(GcRequest request) {
        log.debug("Extension {} is being deleted", request);

        client.fetch(request.gvk(), request.name())
            .filter(deletable())
            .ifPresent(extension -> {
                var extensionStore = converter.convertTo(extension);
                storeClient.delete(extensionStore.getName(), extensionStore.getVersion());
                log.debug("Extension {} was deleted", request);
            });

        return null;
    }

    @Override
    public Controller setupWith(ControllerBuilder builder) {
        var queue = new DefaultDelayQueue<GcRequest>(Instant::now, Duration.ofMillis(500));
        var synchronizer = new GcSynchronizer(client, queue, schemeManager);
        return new DefaultController<>(
            "garbage-collector-controller",
            this,
            queue,
            synchronizer,
            Duration.ofMillis(500),
            Duration.ofSeconds(1000),
            // TODO Make it configurable
            10);
    }

    private Predicate<Extension> deletable() {
        return extension -> CollectionUtils.isEmpty(extension.getMetadata().getFinalizers())
            && extension.getMetadata().getDeletionTimestamp() != null;
    }
}
