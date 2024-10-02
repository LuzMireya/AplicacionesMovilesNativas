package com.samuelbarush.cerradura.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.samuelbarush.cerradura.controller","com.samuelbarush.cerradura.model"})
public class CerraduraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CerraduraApplication.class, args);
	}

}