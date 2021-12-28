package com.convertertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConverterTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterTaskApplication.class, args);
	}

}
