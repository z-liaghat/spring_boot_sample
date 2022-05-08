package com.example.springbootsample.controller;

import com.example.springbootsample.service.FibonacciService;
import com.example.springbootsample.service.FibonacciServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
public class FibonacciController {

    private static final String template = "Hello, %s!";

    @GetMapping("/fibo")
    public long getFibonacci(@RequestParam(value = "length", defaultValue = "1") int length) {
        final FibonacciService fibonacciService = new FibonacciServiceImpl();
        long fibonacciResult = fibonacciService.computeFibonacci(length);
        return fibonacciResult;
    }
}
