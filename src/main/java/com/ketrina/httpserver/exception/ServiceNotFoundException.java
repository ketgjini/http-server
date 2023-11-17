package com.ketrina.httpserver.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(final String message) {
        super(message);
    }
}
