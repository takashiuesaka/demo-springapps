package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContoroller {

    @GetMapping("/")
    public String Hello() {
        return "Hello from Spring App!";
    }
}
