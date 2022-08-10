package com.eidiko.niranjana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableSwagger2
public class JwtUtilsTokenGenerateWithAuthenticationRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtUtilsTokenGenerateWithAuthenticationRestApplication.class, args);
		System.out.println("Swagger Application Started Successfully...");
	}

	@Bean
	public RestTemplate template()
	{
		return new RestTemplate();
	}
}
