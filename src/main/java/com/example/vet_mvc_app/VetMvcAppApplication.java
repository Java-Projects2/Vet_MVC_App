package com.example.vet_mvc_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VetMvcAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetMvcAppApplication.class, args);
    }

}
