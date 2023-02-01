package run.halo.app.search.post;

import lombok.Data;

import java.time.Instant;

@Data
public class PostHit {

    private String name;

    private String title;

    private String content;

    private Instant publishTimestamp;

    private String permalink;

}
