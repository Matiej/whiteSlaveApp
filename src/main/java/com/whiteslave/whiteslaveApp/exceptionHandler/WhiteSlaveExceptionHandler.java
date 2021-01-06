package com.whiteslave.whiteslaveApp.exceptionHandler;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.FileNotFoundException;
import com.whiteslave.whiteslaveApp.controller.headerHandler.HeaderKey;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovException;
import com.whiteslave.whiteslaveApp.user.exception.UserExistException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class WhiteSlaveExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        String message = "Validation error ==> ";
        log.error(message, ex);
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(ex, message, badRequest);
        return ResponseEntity.status(badRequest)
                .headers(getExceptionHeaders(badRequest.name(), message))
                .body(exceptionResponse);
    }

    @ExceptionHandler({MfGovException.class})
    public final ResponseEntity<Object> handleMfGovExceptionnException(MfGovException ex, WebRequest request) {
        String message = "MfGovException error  ==> ";
        log.error(message, ex);
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(ex, message, badRequest);
        return ResponseEntity.status(badRequest)
                .headers(getExceptionHeaders(badRequest.name(), message))
                .body(exceptionResponse);
    }

    @ExceptionHandler({HibernateException.class, FeignException.class, Exception.class})
    public final ResponseEntity<Object> handleHibernateException(RuntimeException rex, WebRequest request) {
        String message = "";
        if (rex instanceof FeignException) {
            message = "Gov resource server error. Can not send and receive any report";
            log.error(message, rex);
        } else if (rex instanceof HibernateException) {
            message = "Data Base server error. Can not get or save any report.";
            log.error(message, rex);
        } else {
            message = "External server error. ";
            log.error(message, rex);
        }
        HttpStatus serviceUnavailable = HttpStatus.SERVICE_UNAVAILABLE;
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(rex, message, serviceUnavailable);
        return ResponseEntity.status(serviceUnavailable)
                .headers(getExceptionHeaders(serviceUnavailable.name(), message))
                .body(exceptionResponse);
    }

    @ExceptionHandler({FileNotFoundException.class})
    public final ResponseEntity<Object> handleFileNotFoundException(RuntimeException rex, WebRequest request) {
        String message = "Report file not found!";
        log.error(message, rex);
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(rex,message, notFound);
        return ResponseEntity.status(notFound)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(getExceptionHeaders(String.valueOf(notFound.value()), message))
                .body(exceptionResponse);
    }

    @ExceptionHandler({UserExistException.class})
    public final ResponseEntity<Object> handleFileUserExistException(RuntimeException rex, WebRequest request) {
        String message = "User exist error.";
        log.error(message, rex);
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ExceptionHandlerResponse exceptionResponse = getExceptionHandlerResponse(rex,message, notFound);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(getExceptionHeaders(String.valueOf(notFound.value()), message))
                .body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "error servlet params ";
        log.error(message, ex);
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionHandlerResponse exceptionHandlerResponse = getExceptionHandlerResponse(ex, message,badRequest );
        return ResponseEntity.status(badRequest)
                .headers(getExceptionHeaders(HttpStatus.BAD_REQUEST.name(), message))
                .body(exceptionHandlerResponse);
    }

    private ExceptionHandlerResponse getExceptionHandlerResponse(Exception ex, String message, HttpStatus httpStatus) {
        String details = ex.getMessage();
        if (ex instanceof ConstraintViolationException) {
            details = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
        }
        return new ExceptionHandlerResponse(LocalDateTime.now().withNano(0), message, details, String.valueOf(httpStatus.value()), httpStatus.name());
    }

    private HttpHeaders getExceptionHeaders(String status, String message) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HeaderKey.STATUS.getHeaderKeyLabel(), status);
        httpHeaders.add(HeaderKey.MESSAGE.getHeaderKeyLabel(), message);
        return httpHeaders;
    }


}
