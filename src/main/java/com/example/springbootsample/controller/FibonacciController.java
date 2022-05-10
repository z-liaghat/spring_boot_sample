package com.example.springbootsample.controller;

import com.example.springbootsample.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("/fibo")
    public String getFibonacci(@RequestParam(value = "length", defaultValue = "1") int length) {
        String fibonacciResult = fibonacciService.computeFibonacci(length);
        return fibonacciResult;
    }
}
