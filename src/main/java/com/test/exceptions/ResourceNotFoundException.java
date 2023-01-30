package com.test.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     * @param message the message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
