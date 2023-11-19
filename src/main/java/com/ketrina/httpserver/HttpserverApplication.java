package com.ketrina.httpserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition
public class HttpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpserverApplication.class, args);
	}

}
