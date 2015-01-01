package com.sloydev.sevibus.domain.exception;

public abstract class SevibusException extends RuntimeException {
    protected SevibusException() {
    }

    protected SevibusException(String message) {
        super(message);
    }

    protected SevibusException(String message, Throwable cause) {
        super(message, cause);
    }

    protected SevibusException(Throwable cause) {
        super(cause);
    }

    protected SevibusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
