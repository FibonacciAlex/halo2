package run.halo.app.core.extension.router;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.router.selector.SelectorUtil;
import run.halo.app.core.extension.Scheme;
import run.halo.app.core.extension.router.IListRequest.QueryListRequest;

import static run.halo.app.core.extension.router.ExtensionRouterFunctionFactory.PathPatternGenerator.buildExtensionPathPattern;

class ExtensionListHandler implements ExtensionRouterFunctionFactory.ListHandler {
    private final Scheme scheme;

    private final ReactiveExtensionClient client;

    public ExtensionListHandler(Scheme scheme, ReactiveExtensionClient client) {
        this.scheme = scheme;
        this.client = client;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> handle(@NonNull ServerRequest request) {
        var listRequest = new QueryListRequest(request.queryParams());
        // TODO Resolve comparator from request
        return client.list(scheme.type(),
                SelectorUtil.labelAndFieldSelectorToPredicate(listRequest.getLabelSelector(),
                    listRequest.getFieldSelector()),
                null,
                listRequest.getPage(),
                listRequest.getSize())
            .flatMap(listResult -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(listResult));
    }

    @Override
    public String pathPattern() {
        return buildExtensionPathPattern(scheme);
    }
}
