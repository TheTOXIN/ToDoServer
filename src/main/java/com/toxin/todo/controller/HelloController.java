package com.toxin.todo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${spring.data.rest.base-path}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        return hello;
    }

}
