package com.sloydev.sevibus.presentation.model;

public class ArrivalTimesModel {

    private String lineName;

    private String nextBusTime;
    private String nextBusDistance;

    private String secondBusTime;
    private String secondBusDistance;

    public String getLineName() {
        return lineName;
    }

    public String getNextBusTime() {
        return nextBusTime;
    }

    public String getNextBusDistance() {
        return nextBusDistance;
    }

    public String getSecondBusTime() {
        return secondBusTime;
    }

    public String getSecondBusDistance() {
        return secondBusDistance;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setNextBusTime(String nextBusTime) {
        this.nextBusTime = nextBusTime;
    }

    public void setNextBusDistance(String nextBusDistance) {
        this.nextBusDistance = nextBusDistance;
    }

    public void setSecondBusTime(String secondBusTime) {
        this.secondBusTime = secondBusTime;
    }

    public void setSecondBusDistance(String secondBusDistance) {
        this.secondBusDistance = secondBusDistance;
    }
}
