package run.halo.app.theme;

import org.springframework.util.Assert;
import run.halo.app.core.extension.Theme;

import java.nio.file.Path;

/**
 * Policy for generating theme directory path.
 *
 * @author guqing
 * @since 2.0.0
 * @deprecated Use {@code run.halo.app.infra.ThemeRootGetter}
 */
@Deprecated(forRemoval = true)
public class ThemePathPolicy {
    public static final String THEME_WORK_DIR = "themes";

    private final Path workDir;

    public ThemePathPolicy(Path workDir) {
        Assert.notNull(workDir, "The halo workDir must not be null.");
        this.workDir = workDir;
    }

    public Path generate(Theme theme) {
        Assert.notNull(theme, "The theme must not be null.");
        String name = theme.getMetadata().getName();
        return themesDir().resolve(name);
    }

    public Path themesDir() {
        return workDir.resolve(ThemePathPolicy.THEME_WORK_DIR);
    }
}
