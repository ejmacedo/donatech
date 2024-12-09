package com.donatech.projetodonatech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.donatech.projetodonatech.properties.StorageProperties;
import com.donatech.projetodonatech.services.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ProjetodonatechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetodonatechApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
