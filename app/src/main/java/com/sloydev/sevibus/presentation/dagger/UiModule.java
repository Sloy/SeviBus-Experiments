package com.sloydev.sevibus.presentation.dagger;

import com.sloydev.sevibus.presentation.view.activity.AppContainer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        library = true
)
public class UiModule {

    @Provides @Singleton AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }
}
