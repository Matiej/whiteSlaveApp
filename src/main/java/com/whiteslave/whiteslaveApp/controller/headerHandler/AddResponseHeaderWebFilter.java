package com.whiteslave.whiteslaveApp.controller.headerHandler;


import com.whiteslave.whiteslaveApp.exceptionHandler.WhiteSlaveExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddResponseHeaderWebFilter implements Filter {

    @Value("${cross.origin.webui}")
    private String originValue;

    private WhiteSlaveExceptionHandler whiteSlaveExceptionHandler;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        int status = res.getStatus();

        if (status == HttpStatus.OK.value() || status == HttpStatus.CREATED.value()) {
            //todo nie działa z tym front
//            res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originValue);
            res.setHeader(HttpHeaders.CONTENT_LANGUAGE, "en-us");
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType());
        }

        filterChain.doFilter(req, res);
    }

}
