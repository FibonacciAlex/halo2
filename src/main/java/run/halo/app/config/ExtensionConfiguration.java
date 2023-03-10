package run.halo.app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import run.halo.app.core.extension.*;
import run.halo.app.core.extension.*;
import run.halo.app.core.extension.controller.DefaultControllerManager;
import run.halo.app.core.extension.router.ExtensionCompositeRouterFunction;

@Configuration(proxyBeanMethods = false)
public class ExtensionConfiguration {

    @Bean
    RouterFunction<ServerResponse> extensionsRouterFunction(ReactiveExtensionClient client,
                                                            SchemeWatcherManager watcherManager) {
        return new ExtensionCompositeRouterFunction(client, watcherManager);
    }

    @Bean
    SchemeManager schemeManager(SchemeWatcherManager watcherManager) {
        return new DefaultSchemeManager(watcherManager);
    }

    @Bean
    SchemeWatcherManager schemeWatcherManager() {
        return new DefaultSchemeWatcherManager();
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(name = "halo.extension.controller.disabled",
        havingValue = "false",
        matchIfMissing = true)
    static class ExtensionControllerConfiguration {

        @Bean
        DefaultControllerManager controllerManager(ExtensionClient client) {
            return new DefaultControllerManager(client);
        }

    }

}
