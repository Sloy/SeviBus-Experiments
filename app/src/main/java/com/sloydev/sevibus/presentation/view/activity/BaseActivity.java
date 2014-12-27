package com.sloydev.sevibus.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import com.sloydev.sevibus.presentation.SevibusApplication;

import javax.inject.Inject;


public abstract class BaseActivity extends ActionBarActivity {

    @Inject AppContainer appContainer;
    protected ViewGroup container;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        container = appContainer.get(this);
        inflateLayout();
        initializeViews();
        created();
    }

    protected void inflateLayout() {
        getLayoutInflater().inflate(getLayoutResource(), container);
    }

    private void injectDependencies() {
        SevibusApplication.get(this).inject(this);
    }

    protected abstract int getLayoutResource();

    protected abstract void initializeViews();

    protected abstract void created();
}
