package run.halo.app.theme.finders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.User;
import run.halo.app.theme.finders.vo.ContributorVo;

import java.util.List;

/**
 * A finder for {@link User}.
 */
public interface ContributorFinder {

    Mono<ContributorVo> getContributor(String name);

    Flux<ContributorVo> getContributors(List<String> names);
}
