package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public String getWelcomeMessage() {
        return "Welcome from the Service Layer!";
    }
}
