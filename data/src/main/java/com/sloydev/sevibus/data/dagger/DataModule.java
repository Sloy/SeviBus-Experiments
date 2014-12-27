package com.sloydev.sevibus.data.dagger;

import dagger.Module;

@Module(
        includes = {
                RepositoryModule.class,
        }
)
public final class DataModule {
}
