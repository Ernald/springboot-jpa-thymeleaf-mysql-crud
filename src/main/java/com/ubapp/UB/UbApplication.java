package com.ubapp.UB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UbApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbApplication.class, args);
		System.out.println("***********");
		System.out.println("UBApp Start");
		System.out.println("***********");
	}
}
