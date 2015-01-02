package com.sloydev.sevibus.data.dagger;

import com.sloydev.sevibus.data.repository.BusLineMemoryRepository;
import com.sloydev.sevibus.data.repository.BusStopMemoryRepository;
import com.sloydev.sevibus.data.repository.MockArrivalTimesRepository;
import com.sloydev.sevibus.data.repository.TussamArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.BusLineRepository;
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

    @Provides @Singleton BusLineRepository provideBusLineRepository(BusLineMemoryRepository instance) {
        return instance;
    }

    @Provides @Singleton ArrivalTimesRepository provideArrivalsRepository(TussamArrivalTimesRepository instance) {
        return instance;
    }
}
