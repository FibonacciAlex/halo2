package run.halo.app.core.extension.controller;

import reactor.core.Disposable;

public interface Controller extends Disposable {

    String getName();

    void start();

}
