package com.example.thisneverends;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ThisneverendsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ThisneverendsApplication.class, args);
	}

}
