package com.whiteslave.whiteslaveApp.config.feignConfiguration;

import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableFeignClients
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class FeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new MfGovErrorDecoder();
    }


}
