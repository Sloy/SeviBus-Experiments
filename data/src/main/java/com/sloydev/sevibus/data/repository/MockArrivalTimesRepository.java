package com.sloydev.sevibus.data.repository;

import com.sloydev.sevibus.domain.ArrivalTimes;
import com.sloydev.sevibus.domain.repository.ArrivalTimesRepository;

import javax.inject.Inject;

public class MockArrivalTimesRepository implements ArrivalTimesRepository {

    @Inject public MockArrivalTimesRepository() {
    }

    @Override public ArrivalTimes getArrivalsForBusStopAndLine(Integer busStopNumber, String lineName) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrivalTimes arrivals = new ArrivalTimes();
        arrivals.setBusStopNumber(busStopNumber);
        arrivals.setBusLineName(lineName);
        arrivals.setNextBus(getNextBus());
        arrivals.setSecondBus(getNextBus());
        return arrivals;
    }

    private ArrivalTimes.BusArrival getNextBus() {
        ArrivalTimes.BusArrival busArrival = new ArrivalTimes.BusArrival(ArrivalTimes.Status.ESTIMATE);
        busArrival.setDistanceInMeters(100);
        busArrival.setTimeInMinutes(4);
        return busArrival;
    }
}