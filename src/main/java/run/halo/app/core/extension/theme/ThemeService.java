package run.halo.app.core.extension.theme;

import reactor.core.publisher.Mono;
import run.halo.app.core.extension.Theme;
import run.halo.app.core.extension.ConfigMap;

import java.io.InputStream;

public interface ThemeService {

    Mono<Theme> install(InputStream is);

    Mono<Theme> upgrade(String themeName, InputStream is);

    Mono<Theme> reloadTheme(String name);

    Mono<ConfigMap> resetSettingConfig(String name);
    // TODO Migrate other useful methods in ThemeEndpoint in the future.

}
