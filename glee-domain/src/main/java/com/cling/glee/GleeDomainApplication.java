package com.cling.glee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GleeDomainApplication {
	public static void main(String[] args) {
		SpringApplication.run(GleeDomainApplication.class, args);
	}
}
