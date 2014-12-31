package com.sloydev.sevibus.domain.repository;

import com.sloydev.sevibus.domain.BusLine;

import java.util.List;

public interface BusLineRepository {

    public List<BusLine> getAllBusLines();

    public List<BusLine> getBusLinesFromStop(Integer busStopNumber);

}
