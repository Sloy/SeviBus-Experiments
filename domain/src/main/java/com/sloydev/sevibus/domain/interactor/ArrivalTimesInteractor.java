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
        //TODO doesn't look very reactive, but how else I know lines for emptyArrivalTimes?
        List<BusLine> linesFromStop = getLinesFromStop(busStopNumber);
        return Observable.from(linesFromStop)
                .map(BusLine::getName)
                .flatMap(lineName -> getArrivals(busStopNumber, lineName))
                .startWith(emptyArrivalTimes(busStopNumber, linesFromStop));
    }

    private Observable<ArrivalTimes> getArrivals(Integer busStopNumber, String lineName) {
        return Observable.just(arrivalsRepository.getArrivalsForBusStopAndLine(busStopNumber, lineName));
    }

    private List<BusLine> getLinesFromStop(Integer busStopNumber) {
        return busLineRepository.getBusLinesFromStop(busStopNumber);
    }

    private Observable<ArrivalTimes> emptyArrivalTimes(Integer busStopNumber, List<BusLine> linesFromStop) {
        return Observable.from(linesFromStop)
                .map(busLine -> {
                    ArrivalTimes arrivalTimes = new ArrivalTimes();
                    arrivalTimes.setBusStopNumber(busStopNumber);
                    arrivalTimes.setBusLineName(busLine.getName());
                    arrivalTimes.setLoading(true);
                    return arrivalTimes;
                });
    }
}
