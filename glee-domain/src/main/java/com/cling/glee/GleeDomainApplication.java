package com.cling.glee;

import com.cling.glee.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(ignoreResourceNotFound = true,
		value = {
				"classpath:application-core-${spring.profiles.active}.yml"
		}, factory = YamlPropertySourceFactory.class)
public class GleeDomainApplication {
	public static void main(String[] args) {
		SpringApplication.run(GleeDomainApplication.class, args);
	}
}
