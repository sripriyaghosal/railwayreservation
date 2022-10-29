package com.capg.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class Trainservice2Application {

	public static void main(String[] args) {
		SpringApplication.run(Trainservice2Application.class, args);
	}

}
