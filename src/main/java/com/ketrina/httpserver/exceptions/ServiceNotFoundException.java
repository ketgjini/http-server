package com.ketrina.httpserver.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(final String message) {
        super(message);
    }
}
