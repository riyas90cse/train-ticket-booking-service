package com.cloud.bees.challenge.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class ServiceErrorController implements ErrorController {

    /**
     * Error Method
     *
     * @param resp httpservlet response
     * @return Error Response
     */
    @RequestMapping("/error")
    public ErrorResponse error(HttpServletResponse resp) {
        return ErrorResponse.builder()
                .statusCode(resp.getStatus())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("We regret. Some issue occurred")
                .details("Page not found in the application. It could be bad request or internal server error")
                .build();
    }
}
