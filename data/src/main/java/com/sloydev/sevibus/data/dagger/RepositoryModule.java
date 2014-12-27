package com.sloydev.sevibus.data.dagger;

import com.sloydev.sevibus.data.repository.BusStopMemoryRepository;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    library = true
)
public final class RepositoryModule {

    @Provides @Singleton BusStopRepository provideBusStopRepository(BusStopMemoryRepository instance) {
        return instance;
    }
}
