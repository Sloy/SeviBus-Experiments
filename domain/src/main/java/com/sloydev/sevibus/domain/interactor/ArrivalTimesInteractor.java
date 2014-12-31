package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.domain.BusLine;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.BusLineRepository;

import java.util.List;

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
        List<BusLine> linesFromStop = getLinesFromStop(busStopNumber);
        return Observable.from(linesFromStop)
                .map(busLine -> busLine.getName())
                .map(lineName -> getArrivalsFrom(busStopNumber, lineName));
    }

    private List<BusLine> getLinesFromStop(Integer busStopNumber) {
        return busLineRepository.getBusLinesFromStop(busStopNumber);
    }

    private ArrivalTimes getArrivalsFrom(Integer busStopNumber, String lineName) {
        return arrivalsRepository.getArrivalsForBusStopAndLine(busStopNumber, lineName);
    }
}
