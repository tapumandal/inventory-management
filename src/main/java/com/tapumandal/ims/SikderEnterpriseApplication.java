package com.tapumandal.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SikderEnterpriseApplication {
	public static void main(String[] args) {
		SpringApplication.run(SikderEnterpriseApplication.class, args);
	}

}
