package run.halo.app.core.extension.attachment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.core.extension.GVK;
import run.halo.app.core.extension.AbstractExtension;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@GVK(group = Constant.GROUP, version = Constant.VERSION, kind = PolicyTemplate.KIND,
    plural = "policytemplates", singular = "policytemplate")
public class PolicyTemplate extends AbstractExtension {

    public static final String KIND = "PolicyTemplate";

    private PolicyTemplateSpec spec;

    @Data
    public static class PolicyTemplateSpec {

        private String displayName;

        @Schema(requiredMode = REQUIRED)
        private String settingName;

    }

}
