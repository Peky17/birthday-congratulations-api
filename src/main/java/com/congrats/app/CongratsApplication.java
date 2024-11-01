package com.congrats.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CongratsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CongratsApplication.class, args);
    }

}
