package run.halo.app.core.extension;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@GVK(group = "", version = "v1alpha1", kind = AnnotationSetting.KIND,
    plural = "annotationsettings", singular = "annotationsetting")
public class AnnotationSetting extends AbstractExtension {
    public static final String TARGET_REF_LABEL = "halo.run/target-ref";

    public static final String KIND = "AnnotationSetting";

    @Schema(requiredMode = REQUIRED)
    private AnnotationSettingSpec spec;

    @Data
    public static class AnnotationSettingSpec {
        @Schema(requiredMode = REQUIRED)
        private GroupKind targetRef;

        @Schema(requiredMode = REQUIRED, minLength = 1)
        private List<Object> formSchema;
    }
}
