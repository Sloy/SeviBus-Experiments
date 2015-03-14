package com.sloydev.sevibus.domain;

import com.sloydev.sevibus.domain.interactor.Interactor;
import com.sloydev.sevibus.domain.interactor.InteractorHandler;

public class TestInteractorHandler implements InteractorHandler {
    @Override public void execute(Interactor interactor) {
        interactor.run();
    }

    @Override public void postResponse(Runnable responseRunnable) {
        responseRunnable.run();
    }
}
