package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.exception.SevibusException;

public interface Interactor extends Runnable{
    interface Callback<Result> {
        void onLoaded(Result result);
    }

    interface ErrorCallback {
        void onError(SevibusException error);
    }

    interface CompleteCallback {
        void onCompleted();
    }
}
