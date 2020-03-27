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
import java.util.Arrays;

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
        String message = prepareMessage(exceptionMessage.getCode(), prepareParamFromMessage(exceptionMessage.getMessage()));
        switch (response.status()) {
            case 400:
                return new MfGovException(message);
            case 404:
                return new NotFoundException(message);
            default:
                return new Exception(message);
        }
    }

    private String prepareMessage(String code, String param) {
        return Arrays.stream(GovErrorCodes.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .map(m -> param != null ? addParamToEnMessage(m.getEnMessage(), param) : m.getEnMessage())
                .orElse("");
    }

    private String addParamToEnMessage(String message, String param) {
        if (message.endsWith(".")) {
            return message.replace(".", " " + param + ".");
        } else {
            return message.trim().concat(" " + param);
        }
    }

    private String prepareParamFromMessage(String message) {
        String paramFromMessage = null;
        if (null != message && message.contains("(") && message.contains(")")) {
            paramFromMessage = message.substring(message.indexOf("("), message.indexOf(")") + 1);
        }

        return paramFromMessage;
    }
}
