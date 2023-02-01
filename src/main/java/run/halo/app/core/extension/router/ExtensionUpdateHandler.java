package run.halo.app.core.extension.router;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.Scheme;
import run.halo.app.core.extension.Unstructured;
import run.halo.app.core.extension.router.ExtensionRouterFunctionFactory.UpdateHandler;

import java.util.Objects;

import static run.halo.app.core.extension.router.ExtensionRouterFunctionFactory.PathPatternGenerator.buildExtensionPathPattern;

class ExtensionUpdateHandler implements UpdateHandler {

    private final Scheme scheme;

    private final ReactiveExtensionClient client;

    ExtensionUpdateHandler(Scheme scheme, ReactiveExtensionClient client) {
        this.scheme = scheme;
        this.client = client;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        String name = request.pathVariable("name");
        return request.bodyToMono(Unstructured.class)
            .filter(unstructured -> unstructured.getMetadata() != null
                && StringUtils.hasText(unstructured.getMetadata().getName())
                && Objects.equals(unstructured.getMetadata().getName(), name))
            .switchIfEmpty(Mono.error(() -> new ServerWebInputException(
                "Cannot read body to " + scheme.groupVersionKind())))
            .flatMap(client::update)
            .flatMap(updated -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updated));
    }

    @Override
    public String pathPattern() {
        return buildExtensionPathPattern(scheme) + "/{name}";
    }
}
