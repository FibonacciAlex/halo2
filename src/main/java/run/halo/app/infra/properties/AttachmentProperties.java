package run.halo.app.infra.properties;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class AttachmentProperties {

    private List<ResourceMapping> resourceMappings = new LinkedList<>();

    @Data
    public static class ResourceMapping {

        /**
         * Like: {@code /upload/**}.
         */
        private String pathPattern;

        /**
         * The location is a relative path to attachments folder in working directory.
         */
        private List<String> locations;

    }
}
