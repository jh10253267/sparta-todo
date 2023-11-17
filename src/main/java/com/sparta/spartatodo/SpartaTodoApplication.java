package com.sparta.spartatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpartaTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaTodoApplication.class, args);
    }

}
