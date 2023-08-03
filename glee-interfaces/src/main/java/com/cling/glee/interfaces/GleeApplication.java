package com.cling.glee.interfaces;

import com.cling.glee.domain.GleeDomainApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GleeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GleeDomainApplication.class, GleeApplication.class)
                .run(args);
    }

}
