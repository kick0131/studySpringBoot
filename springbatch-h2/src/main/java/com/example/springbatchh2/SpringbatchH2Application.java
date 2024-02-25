package com.example.springbatchh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbatchH2Application {

	public static void main(String[] args) {
		System.out.println("file.encoding : "+System.getProperty("file.encoding"));
		SpringApplication.run(SpringbatchH2Application.class, args);
	}

}
