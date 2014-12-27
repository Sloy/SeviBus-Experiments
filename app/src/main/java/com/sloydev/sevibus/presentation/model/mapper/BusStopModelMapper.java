package com.sloydev.sevibus.presentation.model.mapper;

import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.presentation.model.BusStopModel;

import javax.inject.Inject;

public class BusStopModelMapper {

    @Inject public BusStopModelMapper() {
    }

    public BusStopModel transform(BusStop busStop) {
        BusStopModel model = new BusStopModel();
        model.setNumber(busStop.getNumber());
        model.setName(busStop.getName());
        return model;
    }
}
