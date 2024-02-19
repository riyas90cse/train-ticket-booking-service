package com.cloud.bees.challenge.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 345456L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, Error error) {
        super(message, error);
    }
}
