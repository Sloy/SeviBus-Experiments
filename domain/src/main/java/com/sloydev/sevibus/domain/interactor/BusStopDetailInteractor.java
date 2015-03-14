package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.exception.BusStopNotFoundException;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import javax.inject.Inject;

public class BusStopDetailInteractor implements Interactor {

    private final BusStopRepository busStopRepository;
    private final InteractorHandler interactorHandler;
    private Integer busStopNumber;
    private Callback<BusStop> callback;

    @Inject public BusStopDetailInteractor(BusStopRepository busStopRepository, InteractorHandler interactorHandler) {
        this.busStopRepository = busStopRepository;
        this.interactorHandler = interactorHandler;
    }

    public void loadBusStopDetail(Integer busStopNumber, Callback<BusStop> callback) {
        this.busStopNumber = busStopNumber;
        this.callback = callback;
        this.interactorHandler.execute(this);
    }

    @Override public void run() {
        BusStop busStop = getBusStop(busStopNumber);
        if (busStop == null) {
            //TODO should this be thrown by the repository itself?
            throw BusStopNotFoundException.create(busStopNumber);
        }
        notifyResult(busStop);
    }

    private BusStop getBusStop(Integer busStopNumber) {
        return busStopRepository.getBusStopByNumber(busStopNumber);
    }

    private void notifyResult(final BusStop busStop) {
        interactorHandler.postResponse(new Runnable() {
            @Override public void run() {
                callback.onLoaded(busStop);
            }
        });
    }
}
