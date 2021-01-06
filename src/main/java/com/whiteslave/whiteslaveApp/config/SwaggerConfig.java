package com.whiteslave.whiteslaveApp.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${prop.swagger.enabled:false}")
    private boolean enableSwagger;

    @Bean
    public Docket productApi() {
        //TODO nie działa sortowanie w swagger -> znalezc
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.whiteslave.whiteslaveApp"))
//                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
//                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
                .apiDescriptionOrdering(new Ordering<ApiDescription>() {
                    @Override
                    public int compare(ApiDescription tl, ApiDescription tr) {
                        int left = tl.getOperations().size() == 1 ? tl.getOperations().get(0).getPosition() : 0;
                        int right = tr.getOperations().size() == 1 ? tr.getOperations().get(0).getPosition() : 0;
                        int pos = Integer.compare(right, left);
                        if (pos == 0) {
                            pos = tl.getPath().compareTo(tr.getPath()) * -1;
                        }
                        return pos;
                    }
                });
    }

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder().title("White Slave app")
                .description("Application to find out company on gov white list.")
                .version("0.1")
                .contact(new Contact("Matiej", "", "maciek@testaarosa.pl"))
                .license("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }


}
