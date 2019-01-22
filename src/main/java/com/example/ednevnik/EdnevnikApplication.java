package com.example.ednevnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EdnevnikApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdnevnikApplication.class, args);
    }
}
