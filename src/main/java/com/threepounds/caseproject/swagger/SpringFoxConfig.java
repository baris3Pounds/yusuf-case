package com.threepounds.caseproject.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.threepounds"))
        .paths(PathSelectors.any())
        .build();

  }

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("3Pounds Swagger with Spring Boot + Security")
        .version("1.0.0")
        .description("3Pounds Intern Project")
        .contact(new Contact("Yusuf Kaya", "Contact_URL", "yusuf@3pounds.io"))
        .contact(new Contact("Tevfik Uykun", "Contact_URL", "tevfik@3pounds.io"))
        .build();
  }
}