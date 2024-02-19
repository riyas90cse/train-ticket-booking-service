package com.cloud.bees.challenge.exception.handler;

import com.cloud.bees.challenge.exception.ErrorResponse;
import com.cloud.bees.challenge.exception.NotFoundException;
import com.cloud.bees.challenge.exception.ServerException;
import com.cloud.bees.challenge.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    private static final String MSG_TITLE = "Exception Message {}";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponse handleNotFoundException(HttpServletRequest req, ServiceException e) {
        log.info(MSG_TITLE, e.getMessage());
        return ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .details(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ErrorResponse handleBadRequestException(HttpServletRequest req, ServiceException e) {
        log.info(MSG_TITLE, e.getMessage());
        return ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ErrorResponse handleInternalServerException(HttpServletRequest req, ServiceException e) {
        log.info(MSG_TITLE, e.getMessage());
        return ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .details(e.getMessage())
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        log.info(MSG_TITLE, e.getMessage());
        return ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .details(e.getMessage())
                .build();
    }
}
