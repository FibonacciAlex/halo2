package run.halo.app.search.post;

import org.pf4j.ExtensionPoint;
import run.halo.app.search.SearchParam;
import run.halo.app.search.SearchResult;

import java.util.List;
import java.util.Set;

public interface PostSearchService extends ExtensionPoint {

    SearchResult<PostHit> search(SearchParam searchParam) throws Exception;

    void addDocuments(List<PostDoc> posts) throws Exception;

    void removeDocuments(Set<String> postNames) throws Exception;

}
