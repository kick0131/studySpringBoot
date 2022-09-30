package com.example.googlecloudapp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GoogleCloudAppApplication {

	public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(GoogleCloudAppApplication.class, args)) {
			try {
				AccessTokenSample tokencls = ctx.getBean(AccessTokenSample.class);
				tokencls.getAccessToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
