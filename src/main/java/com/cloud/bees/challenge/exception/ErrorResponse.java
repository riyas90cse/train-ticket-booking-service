package com.cloud.bees.challenge.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {

    private int statusCode;

    private HttpStatus status;

    private String message;

    private String details;

}
