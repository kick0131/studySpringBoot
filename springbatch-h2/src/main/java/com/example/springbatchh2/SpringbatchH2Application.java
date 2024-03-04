package com.example.springbatchh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@SpringBootApplication
public class SpringbatchH2Application {

	public static void main(String[] args) {
		System.out.println("file.encoding : "+System.getProperty("file.encoding"));
		SpringApplication.run(SpringbatchH2Application.class, args);
		System.out.println("=== SpringApplication.run() end ===");
	}

}