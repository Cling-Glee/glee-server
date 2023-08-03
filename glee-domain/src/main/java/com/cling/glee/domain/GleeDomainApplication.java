package com.cling.glee.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:application.properties")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GleeDomainApplication {
}
