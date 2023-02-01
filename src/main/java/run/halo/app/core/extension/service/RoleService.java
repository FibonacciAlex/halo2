package run.halo.app.core.extension.service;

import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.app.core.extension.Role;
import run.halo.app.core.extension.RoleBinding.RoleRef;
import run.halo.app.core.extension.RoleBinding.Subject;

import java.util.List;
import java.util.Set;

/**
 * @author guqing
 * @since 2.0.0
 */
public interface RoleService {

    @NonNull
    @Deprecated
    Role getRole(String name);

    Mono<Role> getMonoRole(String name);

    Flux<RoleRef> listRoleRefs(Subject subject);

    List<Role> listDependencies(Set<String> names);
}
