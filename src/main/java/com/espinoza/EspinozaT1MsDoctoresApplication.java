package com.espinoza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EspinozaT1MsDoctoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspinozaT1MsDoctoresApplication.class, args);
	}

}
