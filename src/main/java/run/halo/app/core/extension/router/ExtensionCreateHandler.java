package run.halo.app.core.extension.router;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.Scheme;
import run.halo.app.core.extension.Unstructured;
import run.halo.app.core.extension.exception.ExtensionConvertException;

import java.net.URI;

import static run.halo.app.core.extension.router.ExtensionRouterFunctionFactory.PathPatternGenerator.buildExtensionPathPattern;

class ExtensionCreateHandler implements ExtensionRouterFunctionFactory.CreateHandler {

    private final Scheme scheme;

    private final ReactiveExtensionClient client;

    public ExtensionCreateHandler(Scheme scheme, ReactiveExtensionClient client) {
        this.scheme = scheme;
        this.client = client;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> handle(@NonNull ServerRequest request) {
        return request.bodyToMono(Unstructured.class)
            .switchIfEmpty(Mono.error(() -> new ExtensionConvertException(
                "Cannot read body to " + scheme.groupVersionKind())))
            .flatMap(client::create)
            .flatMap(createdExt -> ServerResponse
                .created(URI.create(pathPattern() + "/" + createdExt.getMetadata().getName()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createdExt));
    }

    @Override
    public String pathPattern() {
        return buildExtensionPathPattern(scheme);
    }
}
