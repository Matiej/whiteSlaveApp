package com.whiteslave.whiteslaveApp.govRequestReport.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;

@Slf4j
public class MfGovErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        Reader reader = null;
        MfGovExceptionMessage exceptionMessage = new MfGovExceptionMessage();
        try {
            reader = response.body().asReader();
            String result = CharStreams.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            exceptionMessage = mapper.readValue(result, MfGovExceptionMessage.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        switch (response.status()) {
            case 400:
                return new MfGovException(exceptionMessage.getMessage());
            case 404:
                return new NotFoundException("FEEEEEIGHEXPTION");
            default:
                return new Exception("Generic error: " + response.request().method());
        }
    }
}
