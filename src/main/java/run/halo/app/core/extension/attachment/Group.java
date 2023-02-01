package run.halo.app.core.extension.attachment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.core.extension.AbstractExtension;
import run.halo.app.core.extension.GVK;

import java.time.Instant;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@GVK(group = Constant.GROUP, version = Constant.VERSION, kind = Group.KIND,
    plural = "groups", singular = "group")
public class Group extends AbstractExtension {

    public static final String KIND = "Group";

    @Schema(required = true)
    private GroupSpec spec;

    private GroupStatus status;

    @Data
    public static class GroupSpec {

        @Schema(required = true, description = "Display name of group")
        private String displayName;

    }

    @Data
    public static class GroupStatus {

        @Schema(description = "Update timestamp of the group")
        private Instant updateTimestamp;

        @Schema(description = "Total of attachments under the current group", minimum = "0")
        private Long totalAttachments;

    }

}
