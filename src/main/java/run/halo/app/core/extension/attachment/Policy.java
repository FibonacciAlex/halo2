package run.halo.app.core.extension.attachment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.core.extension.AbstractExtension;
import run.halo.app.core.extension.GVK;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@GVK(group = Constant.GROUP, version = Constant.VERSION, kind = Policy.KIND,
    plural = "policies", singular = "policy")
public class Policy extends AbstractExtension {

    public static final String KIND = "Policy";

    @Schema(requiredMode = REQUIRED)
    private PolicySpec spec;

    @Data
    public static class PolicySpec {

        @Schema(requiredMode = REQUIRED, description = "Display name of policy")
        private String displayName;

        @Schema(requiredMode = REQUIRED, description = "Reference name of PolicyTemplate")
        private String templateName;

        @Schema(description = "Reference name of ConfigMap extension")
        private String configMapName;

    }

}
