package run.halo.app.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchResult<T> {
    private List<T> hits;
    private String keyword;
    private Long total;
    private int limit;
    private long processingTimeMillis;
}
