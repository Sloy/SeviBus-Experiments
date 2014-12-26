package com.sloydev.sevibus.presentation.model.mapper;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.presentation.model.BusStopModel;

public class BusStopModelMapper {
    public BusStopModel transform(BusStop busStop) {
        BusStopModel model = new BusStopModel();
        model.setNumber(busStop.getNumber());
        model.setName(busStop.getName());
        return model;
    }
}
