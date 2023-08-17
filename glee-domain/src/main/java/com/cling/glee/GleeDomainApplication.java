package com.cling.glee;

import com.cling.glee.domain.entity.BlockUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Block;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GleeDomainApplication {
	public static void main(String[] args) {
		SpringApplication.run(GleeDomainApplication.class, args);
	}

}
