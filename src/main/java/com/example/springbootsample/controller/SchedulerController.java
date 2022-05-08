package com.example.springbootsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

    @GetMapping("/scheduler")
    public void getFibonacci(@RequestParam(value = "timestamp", defaultValue = "1") long timestamp) {


    }
}
