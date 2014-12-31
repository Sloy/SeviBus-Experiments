package com.sloydev.sevibus.domain.repository;

import com.sloydev.sevibus.domain.ArrivalTimes;

public interface ArrivalTimesRepository {

    public ArrivalTimes getArrivalsForBusStopAndLine(Integer busStopNumber, String lineName);
}
