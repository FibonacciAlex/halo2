package run.halo.app.core.extension;

import org.springframework.lang.NonNull;

import java.util.List;

public interface SchemeWatcherManager {

    void register(@NonNull SchemeWatcher watcher);

    void unregister(@NonNull SchemeWatcher watcher);

    List<SchemeWatcher> watchers();

    interface SchemeWatcher {

        void onChange(ChangeEvent event);

    }

    interface ChangeEvent {

    }

    class SchemeRegistered implements ChangeEvent {
        private final Scheme newScheme;

        public SchemeRegistered(Scheme newScheme) {
            this.newScheme = newScheme;
        }

        public Scheme getNewScheme() {
            return newScheme;
        }
    }

    class SchemeUnregistered implements ChangeEvent {

        private final Scheme deletedScheme;

        public SchemeUnregistered(Scheme deletedScheme) {
            this.deletedScheme = deletedScheme;
        }

        public Scheme getDeletedScheme() {
            return deletedScheme;
        }

    }
}
