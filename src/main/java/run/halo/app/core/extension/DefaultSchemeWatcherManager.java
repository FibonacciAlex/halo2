package run.halo.app.core.extension;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultSchemeWatcherManager implements SchemeWatcherManager {

    private final List<SchemeWatcher> watchers;

    public DefaultSchemeWatcherManager() {
        watchers = new CopyOnWriteArrayList<>();
    }

    @Override
    public void register(@NonNull SchemeWatcher watcher) {
        Assert.notNull(watcher, "Scheme watcher must not be null");
        watchers.add(watcher);
    }

    @Override
    public void unregister(@NonNull SchemeWatcher watcher) {
        Assert.notNull(watcher, "Scheme watcher must not be null");
        watchers.remove(watcher);
    }

    @Override
    public List<SchemeWatcher> watchers() {
        // we have to copy the watchers entirely to prevent concurrent modification.
        return Collections.unmodifiableList(watchers);
    }
}
