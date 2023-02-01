package run.halo.app.security.authentication.jwt;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public class LoginAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private final ServerResponse.Context context;

    public LoginAuthenticationFailureHandler(ServerResponse.Context context) {
        this.context = context;
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange,
        AuthenticationException exception) {
        return ServerResponse.badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                Map.of("templates/error", exception.getLocalizedMessage())
            )
            .flatMap(serverResponse ->
                serverResponse.writeTo(webFilterExchange.getExchange(), context));
    }

}
