package run.halo.app.infra;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import run.halo.app.core.extension.*;
import run.halo.app.core.extension.attachment.Attachment;
import run.halo.app.core.extension.attachment.Group;
import run.halo.app.core.extension.attachment.Policy;
import run.halo.app.core.extension.attachment.PolicyTemplate;
import run.halo.app.core.extension.content.*;
import run.halo.app.core.extension.ConfigMap;
import run.halo.app.core.extension.SchemeManager;
import run.halo.app.search.extension.SearchEngine;
import run.halo.app.security.authentication.pat.PersonalAccessToken;

@Component
public class SchemeInitializer implements ApplicationListener<ApplicationStartedEvent> {

    private final SchemeManager schemeManager;

    private final ApplicationEventPublisher eventPublisher;

    public SchemeInitializer(SchemeManager schemeManager,
        ApplicationEventPublisher eventPublisher) {
        this.schemeManager = schemeManager;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        schemeManager.register(Role.class);
        schemeManager.register(PersonalAccessToken.class);

        // plugin.halo.run
        schemeManager.register(Plugin.class);
        schemeManager.register(SearchEngine.class);

        schemeManager.register(RoleBinding.class);
        schemeManager.register(User.class);
        schemeManager.register(ReverseProxy.class);
        schemeManager.register(Setting.class);
        schemeManager.register(AnnotationSetting.class);
        schemeManager.register(ConfigMap.class);
        schemeManager.register(Theme.class);
        schemeManager.register(Menu.class);
        schemeManager.register(MenuItem.class);
        schemeManager.register(Post.class);
        schemeManager.register(Category.class);
        schemeManager.register(Tag.class);
        schemeManager.register(Snapshot.class);
        schemeManager.register(Comment.class);
        schemeManager.register(Reply.class);
        schemeManager.register(SinglePage.class);
        // storage.halo.run
        schemeManager.register(Group.class);
        schemeManager.register(Policy.class);
        schemeManager.register(Attachment.class);
        schemeManager.register(PolicyTemplate.class);
        // metrics.halo.run
        schemeManager.register(Counter.class);

        eventPublisher.publishEvent(new SchemeInitializedEvent(this));
    }
}
