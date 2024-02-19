package com.cloud.bees.challenge.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class ServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 345456L;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(String message, Error error) {
        super(message, error);
    }
}
