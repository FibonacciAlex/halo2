package run.halo.app.theme.engine;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.stereotype.Component;
import run.halo.app.theme.ThemeContext;

import java.nio.file.Files;

@Component
public class DefaultThemeTemplateAvailabilityProvider implements ThemeTemplateAvailabilityProvider {

    private final ThymeleafProperties thymeleafProperties;

    public DefaultThemeTemplateAvailabilityProvider(ThymeleafProperties thymeleafProperties) {
        this.thymeleafProperties = thymeleafProperties;
    }

    @Override
    public boolean isTemplateAvailable(ThemeContext themeContext, String viewName) {
        var suffix = thymeleafProperties.getSuffix();
        // Currently, we only support Path here.
        var path = themeContext.getPath().resolve("templates").resolve(viewName + suffix);
        return Files.exists(path);
    }

}
