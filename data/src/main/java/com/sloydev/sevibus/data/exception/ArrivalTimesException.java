package com.sloydev.sevibus.data.exception;

import com.sloydev.sevibus.domain.exception.SevibusException;

public class ArrivalTimesException extends SevibusException{


    private final Integer busStopNumber;
    private final String lineName;

    public ArrivalTimesException(Throwable cause, Integer busStopNumber, String lineName) {
        super(cause);
        this.busStopNumber = busStopNumber;
        this.lineName = lineName;
    }

    public Integer getBusStopNumber() {
        return busStopNumber;
    }

    public String getLineName() {
        return lineName;
    }
}
