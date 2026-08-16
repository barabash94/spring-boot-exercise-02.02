package com.bara.spring_boot_exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExerciseApplication.class, args);
	}

}
