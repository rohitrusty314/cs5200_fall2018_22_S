package com.northeastern.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MovieDatabaseSystemApplication   extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder
    configure(SpringApplicationBuilder application) {
        return application.sources(
                MovieDatabaseSystemApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MovieDatabaseSystemApplication.class, args);
    }
}
