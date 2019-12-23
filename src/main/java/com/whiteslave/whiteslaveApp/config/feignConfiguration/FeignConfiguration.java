//package com.whiteslave.whiteslaveApp.config.feignConfiguration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Feign;
//import feign.Logger;
//import feign.codec.Decoder;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignAutoConfiguration;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//@Configuration
////@EnableFeignClients
////@ImportAutoConfiguration({FeignAutoConfiguration.class})
//public class FeignConfiguration {
//
////    @Bean
////    @Scope("prototype")
////    public Feign.Builder feignBuilder() {
////        return Feign.builder();
////    }
//
////    @Bean
////    public Decoder feignDecoder() {
////        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
////        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
////        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
////    }
//
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//}
