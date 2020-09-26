package org.travelbook.backend.utils;

public class TravelBookException extends Exception {

    public TravelBookException(String message) {
        super(message);
    }

    public TravelBookException(String message, Throwable ex) {
        super(message, ex);
    }
}
