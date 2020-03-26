//package com.whiteslave.whiteslaveApp.govRequestReport.client;
//
//import feign.Logger;
//import feign.codec.ErrorDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MfGovWhiteListConfiguration {
//
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
////    @Bean
////    public ErrorDecoder errorDecoder() {
////        return new ErrorDecoder.Default();
////    }
//
//
////    @Bean
////    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverter) {
////        return new ErrorDecoder(new SpringDecoder(messageConverter));
////    }
////
////    class ErrorDecoder implements Decoder {
////        private final Decoder delegate;
////
////        public ErrorDecoder(Decoder delegate) {
////            this.delegate = delegate;
////        }
////
////        @Override
////        public Object decode(feign.Response response, Type type) throws IOException, DecodeException, FeignException {
////            Object decodeObject = delegate.decode(response, type);
////
//////            if(decodeObject instanceof MfGovResponse) {
//////                MfGovResponse decodeResponse = (MfGovResponse) decodeObject;
//////                if(decodeResponse.)
//////            }
////            return null;
////        }
////    }
//}
