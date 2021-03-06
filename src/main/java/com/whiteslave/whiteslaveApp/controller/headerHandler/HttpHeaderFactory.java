package com.whiteslave.whiteslaveApp.controller.headerHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class HttpHeaderFactory {

    public static HttpHeaders getSuccessfulHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET");
        httpHeaders.add(HeaderKey.STATUS.getHeaderKeyLabel(), String.valueOf(HttpStatus.OK.value()));
        httpHeaders.add(HeaderKey.MESSAGE.getHeaderKeyLabel(), "Successful");
        return httpHeaders;

//        httpHeaders.add(HttpHeaders.ORIGIN, "http://localhost:4200/");
//        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
//        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,"Content-Type, Authorization");
    }
}
