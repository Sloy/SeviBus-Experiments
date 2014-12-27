package com.sloydev.sevibus.presentation;

import android.app.Application;

import com.sloydev.sevibus.data.dagger.DataModule;
import com.sloydev.sevibus.presentation.dagger.PresentationModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                PresentationModule.class,
                DataModule.class,
        },
        injects = {
                SevibusApplication.class,
        },
        library = true
)
public final class SevibusModule {

    private final SevibusApplication app;

    public SevibusModule(SevibusApplication app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }
}
