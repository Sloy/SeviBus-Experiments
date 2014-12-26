package com.sloydev.sevibus.domain.repository;

import com.sloydev.sevibus.domain.BusStop;

import java.util.List;

public interface BusStopRepository {

    public BusStop getBusStopByNumber(Integer number);

    public List<BusStop> getAllBusStops();

    public void addBusStop(BusStop busStop);

}
