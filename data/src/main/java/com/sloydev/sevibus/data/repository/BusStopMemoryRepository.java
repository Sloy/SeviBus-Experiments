package com.sloydev.sevibus.data.repository;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import java.util.ArrayList;
import java.util.List;

public class BusStopMemoryRepository implements BusStopRepository {

    private final List<BusStop> busStops;

    public BusStopMemoryRepository() {
        this.busStops = new ArrayList<>();
        initializeMockData();
    }

    @Override public BusStop getBusStopByNumber(Integer number) {
        for (BusStop busStop : busStops) {
            if (busStop.getNumber().equals(number)) {
                return busStop;
            }
        }
        return null;
    }

    @Override public List<BusStop> getAllBusStops() {
        return new ArrayList<>(busStops);
    }

    @Override public void addBusStop(BusStop busStop) {
        busStops.add(busStop);
    }

    private void initializeMockData() {
        BusStop b1 = new BusStop();
        b1.setNumber(50);
        b1.setName("Cruz Roja");

        BusStop b2 = new BusStop();
        b2.setNumber(63);
        b2.setName("Escuela Técnica Superior de Ingeniería Informática");

        addBusStop(b1);
        addBusStop(b2);
    }
}
