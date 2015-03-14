package com.sloydev.sevibus.domain.interactor;

public interface InteractorHandler {
    void execute(Interactor interactor);

    void postResponse(Runnable responseRunnable);
}
