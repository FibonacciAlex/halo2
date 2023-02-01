package run.halo.app.plugin;

import java.lang.annotation.*;

/**
 * Api version.
 *
 * @author guqing
 * @since 2.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * Api version value.
     *
     * @return api version string
     */
    String value();
}
