package run.halo.app.core.extension.router;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.Scheme;

import static run.halo.app.core.extension.router.ExtensionRouterFunctionFactory.PathPatternGenerator.buildExtensionPathPattern;

class ExtensionDeleteHandler implements ExtensionRouterFunctionFactory.DeleteHandler {

    private final Scheme scheme;

    private final ReactiveExtensionClient client;

    ExtensionDeleteHandler(Scheme scheme, ReactiveExtensionClient client) {
        this.scheme = scheme;
        this.client = client;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        var name = request.pathVariable("name");
        return client.get(scheme.type(), name)
            .flatMap(client::delete)
            .flatMap(deleted -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(deleted));
    }

    @Override
    public String pathPattern() {
        return buildExtensionPathPattern(scheme) + "/{name}";
    }

}
