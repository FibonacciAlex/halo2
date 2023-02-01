package run.halo.app.security.authentication;

import org.springframework.http.MediaType;
import org.springframework.security.web.server.util.matcher.MediaTypeServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;

import java.util.Set;

public enum WebExchangeMatchers {
    ;

    public static ServerWebExchangeMatcher ignoringMediaTypeAll(MediaType... matchingMediaTypes) {
        var matcher = new MediaTypeServerWebExchangeMatcher(matchingMediaTypes);
        matcher.setIgnoredMediaTypes(Set.of(MediaType.ALL));
        return matcher;
    }
}
