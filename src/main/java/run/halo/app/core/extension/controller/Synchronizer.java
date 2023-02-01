package run.halo.app.core.extension.controller;

import reactor.core.Disposable;

public interface Synchronizer<R> extends Disposable {

    void start();

}
