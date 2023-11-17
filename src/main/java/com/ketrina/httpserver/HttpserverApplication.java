package com.ketrina.httpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HttpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpserverApplication.class, args);
	}

}
