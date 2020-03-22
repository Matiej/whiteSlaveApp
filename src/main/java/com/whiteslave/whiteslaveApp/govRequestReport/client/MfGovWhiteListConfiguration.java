package com.whiteslave.whiteslaveApp.govRequestReport.client;

import feign.FeignException;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;

public class MfGovWhiteListConfiguration {

    @Bean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverter) {
        return new ErrorDecoder(new SpringDecoder(messageConverter));
    }

    class ErrorDecoder implements Decoder {
        private final Decoder delegate;

        public ErrorDecoder(Decoder delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object decode(feign.Response response, Type type) throws IOException, DecodeException, FeignException {
            Object decodeObject = delegate.decode(response, type);

//            if(decodeObject instanceof MfGovResponse) {
//                MfGovResponse decodeResponse = (MfGovResponse) decodeObject;
//                if(decodeResponse.)
//            }
            return null;
        }
    }
}
