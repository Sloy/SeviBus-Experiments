package com.sloydev.sevibus.domain.interactor;

public class TestInteractorHandler implements InteractorHandler {
    @Override public void execute(Interactor interactor) {
        interactor.run();
    }

    @Override public void postResponse(Runnable responseRunnable) {
        responseRunnable.run();
    }
}
