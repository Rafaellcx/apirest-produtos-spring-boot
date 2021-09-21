package com.produtos.apirest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.produtos.apirest"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Produtos API REST")
                .description("\"API REST de cadastro de produtos\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Rafael Lessa de Castro Xavier", "https://github.com/Rafaellcx", "rafaellessacastro@hotmail.com"))
                .build();
    }

//    private List<ResponseMessage> responseMessageForGET()
//    {
//        return new ArrayList<ResponseMessage>() {{
//            add(new ResponseMessageBuilder()
//                    .code(500)
//                    .message("500 message")
//                    .responseModel(new ModelRef("Error"))
//                    .build());
//            add(new ResponseMessageBuilder()
//                    .code(403)
//                    .message("Forbidden!")
//                    .build());
//        }};
//    }
}
