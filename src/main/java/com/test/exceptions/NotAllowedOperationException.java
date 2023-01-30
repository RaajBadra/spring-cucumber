package com.test.exceptions;

public class NotAllowedOperationException extends RuntimeException {

    /**
     *
     * @param message the message
     */
    public NotAllowedOperationException(final String message) {
        super(message);
    }
}
