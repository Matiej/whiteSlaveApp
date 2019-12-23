package com.whiteslave.whiteslaveApp.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RestControllerAdvice
public class WhtieSlaveExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class,})
    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(ex, request);
        log.error("error message details ==> ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionHandlerResponse exceptionHandlerResponse = getExceptionHandlerResponse(ex, request);
        log.error("error message details ==> ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandlerResponse);
    }

    private ExceptionHandlerResponse getExceptionHandlerResponse(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        if (ex instanceof ConstraintViolationException) {
            message = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
        }
        return new ExceptionHandlerResponse(LocalDateTime.now().withNano(0), message,
                request.getDescription(false));
    }

}
