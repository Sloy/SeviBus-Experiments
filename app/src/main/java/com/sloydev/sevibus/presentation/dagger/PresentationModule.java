package com.sloydev.sevibus.presentation.dagger;

import com.sloydev.sevibus.presentation.view.activity.BaseActivity;
import com.sloydev.sevibus.presentation.view.activity.BusStopDetailActivity;
import com.sloydev.sevibus.presentation.view.activity.MainActivity;

import dagger.Module;

@Module(
        injects = {
                BaseActivity.class,
                MainActivity.class,
                BusStopDetailActivity.class,
        },
        includes = {
                UiModule.class,
        },
        complete = false
)
public final class PresentationModule {
}
