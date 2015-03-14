package com.sloydev.sevibus.domain;

import com.sloydev.sevibus.domain.interactor.Interactor;

import java.util.ArrayList;
import java.util.List;

public class SpyCallback<Result> implements Interactor.Callback<Result> {

    public List<Result> results = new ArrayList<>();

    @Override public void onLoaded(Result result) {
        results.add(result);
    }
}
