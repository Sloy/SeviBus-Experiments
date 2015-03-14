package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.domain.BusLine;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;
import com.sloydev.sevibus.domain.repository.BusLineRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArrivalTimesInteractor implements Interactor {

    private final BusLineRepository busLineRepository;
    private final ArrivalTimesRepository arrivalsRepository;
    private final InteractorHandler interactorHandler;
    private Integer busStopNumber;
    private Callback<ArrivalTimes> callback;

    @Inject public ArrivalTimesInteractor(BusLineRepository busLineRepository, ArrivalTimesRepository arrivalsRepository, InteractorHandler interactorHandler) {
        this.busLineRepository = busLineRepository;
        this.arrivalsRepository = arrivalsRepository;
        this.interactorHandler = interactorHandler;
    }

    public void loadArrivals(Integer busStopNumber, Callback<ArrivalTimes> callback, ErrorCallback errorCallback, CompleteCallback completeCallback) {
        this.busStopNumber = busStopNumber;
        this.callback = callback;
        this.interactorHandler.execute(this);
    }

    @Override public void run() {
        List<BusLine> linesFromStop = getLinesFromStop(busStopNumber);
        for (BusLine busLine : linesFromStop) {
            String name = busLine.getName();
            ArrivalTimes arrivalsForLine = getArrivals(busStopNumber, name);
            notifyLoaded(arrivalsForLine);
        }
    }

    private ArrivalTimes getArrivals(Integer busStopNumber, String lineName) {
        return arrivalsRepository.getArrivalsForBusStopAndLine(busStopNumber, lineName);
    }

    private List<BusLine> getLinesFromStop(Integer busStopNumber) {
        return busLineRepository.getBusLinesFromStop(busStopNumber);
    }

    private List<ArrivalTimes> emptyArrivalTimes(Integer busStopNumber, List<BusLine> linesFromStop) {
        List<ArrivalTimes> emptyArrivals = new ArrayList<>();
        for (BusLine busLine : linesFromStop) {
            ArrivalTimes arrivalTimes = new ArrivalTimes();
            arrivalTimes.setBusStopNumber(busStopNumber);
            arrivalTimes.setBusLineName(busLine.getName());
            arrivalTimes.setLoading(true);
            emptyArrivals.add(arrivalTimes);
        }
        return emptyArrivals;
    }

    private void notifyLoaded(final ArrivalTimes arrivalsForLine) {
        interactorHandler.postResponse(new Runnable() {
            @Override public void run() {
                callback.onLoaded(arrivalsForLine);
            }
        });
    }
}
