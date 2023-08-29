package com.cling.glee.interfaces;

import com.cling.glee.GleeDomainApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.cling.glee")
public class GleeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GleeDomainApplication.class, GleeApplication.class)
                .run(args);
    }
}
