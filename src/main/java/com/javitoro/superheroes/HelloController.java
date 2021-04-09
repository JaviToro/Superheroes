package com.javitoro.superheroes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "hello", description = "the greeting API")
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Get a message saying hello and introducing the API")
    public String index() {
        return "Hello! This is a demo Spring Boot 2 and Java 11 API requested by Hiberus/W2M for their technical test.";
    }

}