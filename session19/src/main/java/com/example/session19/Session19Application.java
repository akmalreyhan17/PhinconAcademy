package com.example.session19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Session19Application {

	public static void main(String[] args) {
		SpringApplication.run(Session19Application.class, args);
	}

}
