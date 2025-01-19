package com.microservices.microservices;

import org.springframework.boot.SpringApplication;

public class TestMicroservicesApplication {

	public static void main(String[] args) {
		 SpringApplication.from(MicroservicesApplication::main).run(args);
	}

}
