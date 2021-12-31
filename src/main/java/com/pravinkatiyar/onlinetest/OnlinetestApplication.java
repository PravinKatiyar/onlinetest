package com.pravinkatiyar.onlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OnlinetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinetestApplication.class, args);
	}

}
