package com.ketrina.httpserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class to start the HTTP Server Application.
 * The @EnableCaching annotation enables Spring's caching support.
 * The @OpenAPIDefinition annotation is used to configure OpenAPI documentation generation.
 *
 * @author Ketrina Gjini
 */
@SpringBootApplication
@EnableCaching
@OpenAPIDefinition
public class HttpserverApplication {

	/**
	 * Main method to start the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(HttpserverApplication.class, args);
	}

}
