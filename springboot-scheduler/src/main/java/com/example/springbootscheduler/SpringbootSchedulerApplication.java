package com.example.springbootscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringbootSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSchedulerApplication.class, args);
	}

}
