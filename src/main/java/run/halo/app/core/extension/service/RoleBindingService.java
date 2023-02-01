package run.halo.app.core.extension.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author guqing
 * @since 2.0.0
 */
@FunctionalInterface
public interface RoleBindingService {

    Set<String> listBoundRoleNames(Collection<? extends GrantedAuthority> authorities);

}
