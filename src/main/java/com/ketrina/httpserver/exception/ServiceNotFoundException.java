package com.ketrina.httpserver.exception;

/**
 * Custom exception class to represent an exception when a service is not found.
 *
 * @author Ketrina
 */
public class ServiceNotFoundException extends RuntimeException {

    /**
     * Constructor to create a ServiceNotFoundException with a specific message.
     */
    public ServiceNotFoundException(final String message) {
        super(message);
    }
}
