package com.javitoro.superheroes;

import com.javitoro.superheroes.configuration.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SuperheroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesApplication.class, args);
    }

}
