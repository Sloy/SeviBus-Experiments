package com.sloydev.sevibus.domain;

import com.sloydev.sevibus.domain.interactor.Interactor;

public class SpyCallback<Result> implements Interactor.Callback<Result> {

    public Result result;

    @Override public void onLoaded(Result result) {
        this.result = result;
    }
}
