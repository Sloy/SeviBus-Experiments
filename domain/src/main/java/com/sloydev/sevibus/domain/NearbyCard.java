package com.sloydev.sevibus.domain;

public class NearbyCard {

    private Integer distance;

    private BusStop busStop;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }
}
