package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.domain.BusLine;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.BusLineRepository;

import javax.inject.Inject;

import rx.Observable;

public class ArrivalTimesInteractor implements Interactor {

    private final BusLineRepository busLineRepository;
    private final ArrivalTimesRepository arrivalsRepository;

    @Inject public ArrivalTimesInteractor(BusLineRepository busLineRepository, ArrivalTimesRepository arrivalsRepository) {
        this.busLineRepository = busLineRepository;
        this.arrivalsRepository = arrivalsRepository;
    }

    public Observable<ArrivalTimes> loadArrivals(Integer busStopNumber) {
        return Observable.just(busStopNumber)
                .flatMap(this::getLinesFromStop)
                .map(BusLine::getName)
                .flatMap(lineName -> getArrivals(busStopNumber, lineName));
    }

    private Observable<ArrivalTimes> getArrivals(Integer busStopNumber, String lineName) {
        return Observable.just(arrivalsRepository.getArrivalsForBusStopAndLine(busStopNumber, lineName));
    }

    private Observable<BusLine> getLinesFromStop(Integer busStopNumber) {
        return Observable.from(busLineRepository.getBusLinesFromStop(busStopNumber));
    }
}
