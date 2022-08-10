package com.eidiko.niranjana.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocsConfig {
	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	@Bean
	public Docket createDocket() {
		//return new Docket(DocumentationType.SWAGGER_2) // UI screen type
//				.select() // to specify RestControllers
//				.apis(RequestHandlerSelectors.basePackage("com.eidiko.niranjana.controller")) // base pkg for
//																								// RestContrllers
//				.paths(PathSelectors.regex("/helloWorld")) // to specify request paths
//				.build(); // builds the Docket obj
		// .useDefaultResponseMessages(true);
				
	return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true)
			.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.basePackage("com.eidiko.niranjana"))
						.paths(PathSelectors.any()).build();


	}
	
	

}


//