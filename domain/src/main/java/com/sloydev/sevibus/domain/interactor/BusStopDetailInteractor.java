package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.exception.BusStopNotFoundException;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import javax.inject.Inject;

import rx.Observable;

public class BusStopDetailInteractor implements Interactor {

    private final BusStopRepository busStopRepository;

    @Inject public BusStopDetailInteractor(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    public Observable<BusStop> loadBusStopDetail(Integer busStopNumber) {
        return Observable.create(subscriber -> {
            BusStop busStop = getBusStop(busStopNumber);
            if (busStop == null) {
                //TODO should this be thrown by the repository itself?
                throw BusStopNotFoundException.create(busStopNumber);
            }
            subscriber.onNext(busStop);
            subscriber.onCompleted();
        });
    }

    private BusStop getBusStop(Integer busStopNumber) {
        return busStopRepository.getBusStopByNumber(busStopNumber);
    }
}
