package com.sloydev.sevibus.domain.exception;

public class BusStopNotFoundException extends SevibusException {

    private BusStopNotFoundException(String message) {
        super(message);
    }

    public static BusStopNotFoundException create(Integer number) {
        String message = String.format("Bus stop with number %d not found", number);
        return new BusStopNotFoundException(message);
    }
}
