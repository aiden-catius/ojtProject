package com.catius.ojtproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(Arrays.asList(securityScheme()))
            .securityContexts(securityContexts())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.catius.ojtproject"))
            .paths(PathSelectors.any())
            .build();
  }

  @Bean
  public UiConfiguration swaggerUiConfig() {
    return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build();
  }

  private SecurityScheme securityScheme() {
    return new ApiKey("oauth2", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
  }

  private List<SecurityReference> defaultAuth() {
    return singletonList(
            new SecurityReference("oauth2", scopes()));
  }

  private AuthorizationScope[] scopes() {
    AuthorizationScope[] scopes = {
            new AuthorizationScope("read", "for read operations"),
            new AuthorizationScope("write", "for write operations")
    };
    return scopes;
  }


  private List<SecurityContext> securityContexts() {
    List<SecurityContext> securityContexts = new ArrayList<>();
    securityContexts.add(securityContext());

    return securityContexts;
  }

}
