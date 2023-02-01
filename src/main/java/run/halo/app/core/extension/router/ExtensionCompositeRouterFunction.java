package run.halo.app.core.extension.router;

import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.ReactiveExtensionClient;
import run.halo.app.core.extension.Scheme;
import run.halo.app.core.extension.SchemeWatcherManager;
import run.halo.app.core.extension.SchemeWatcherManager.SchemeWatcher;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtensionCompositeRouterFunction implements
    RouterFunction<ServerResponse>, SchemeWatcher {

    private final Map<Scheme, RouterFunction<ServerResponse>> schemeRouterFuncMapper;

    private final ReactiveExtensionClient client;

    public ExtensionCompositeRouterFunction(ReactiveExtensionClient client,
        SchemeWatcherManager watcherManager) {
        this.client = client;
        schemeRouterFuncMapper = new ConcurrentHashMap<>();
        if (watcherManager != null) {
            watcherManager.register(this);
        }
    }

    @Override
    @NonNull
    public Mono<HandlerFunction<ServerResponse>> route(@NonNull ServerRequest request) {
        return Flux.fromIterable(getRouterFunctions())
            .concatMap(routerFunction -> routerFunction.route(request))
            .next();
    }

    @Override
    public void accept(@NonNull RouterFunctions.Visitor visitor) {
        getRouterFunctions().forEach(routerFunction -> routerFunction.accept(visitor));
    }

    private Iterable<RouterFunction<ServerResponse>> getRouterFunctions() {
        // TODO Copy router functions here
        return Collections.unmodifiableCollection(schemeRouterFuncMapper.values());
    }

    @Override
    public void onChange(SchemeWatcherManager.ChangeEvent event) {
        if (event instanceof SchemeWatcherManager.SchemeRegistered registeredEvent) {
            var scheme = registeredEvent.getNewScheme();
            var factory = new ExtensionRouterFunctionFactory(scheme, client);
            this.schemeRouterFuncMapper.put(scheme, factory.create());
        } else if (event instanceof SchemeWatcherManager.SchemeUnregistered unregisteredEvent) {
            this.schemeRouterFuncMapper.remove(unregisteredEvent.getDeletedScheme());
        }
    }
}
