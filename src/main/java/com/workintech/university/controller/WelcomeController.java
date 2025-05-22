package com.workintech.university.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
@AllArgsConstructor
public class WelcomeController {

    @GetMapping
    public String sayHello(){
        return "Hello World";
    }
}
