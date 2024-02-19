package com.cloud.bees.challenge.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 345456L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Error error) {
        super(message, error);
    }
}
