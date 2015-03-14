package com.sloydev.sevibus.data.dagger;

import com.sloydev.sevibus.data.executor.InteractorExecutor;
import com.sloydev.sevibus.domain.interactor.InteractorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                RepositoryModule.class,
        },
        library = true,
        complete = false
)
public final class DataModule {
    @Provides @Singleton InteractorHandler provideInteractorHandler(InteractorExecutor interactorExecutor) {
        return interactorExecutor;
    }
}
