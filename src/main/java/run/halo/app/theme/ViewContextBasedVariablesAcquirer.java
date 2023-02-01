package run.halo.app.theme;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@FunctionalInterface
public interface ViewContextBasedVariablesAcquirer {

    Mono<Map<String, Object>> acquire(ServerWebExchange exchange);
}
