package com.sloydev.sevibus.domain.interactor;

import com.sloydev.sevibus.domain.BusStop;
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
            subscriber.onNext(getBusStop(busStopNumber));
            subscriber.onCompleted();
        });
    }

    private BusStop getBusStop(Integer busStopNumber) {
        return busStopRepository.getBusStopByNumber(busStopNumber);
    }
}
