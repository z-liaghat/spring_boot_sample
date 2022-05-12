package com.example.springbootsample.controller;

import com.example.springbootsample.service.CronSchedulerService;
import com.example.springbootsample.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulerController {

    @Autowired
    private CronSchedulerService cronSchedulerService;

    @GetMapping("/scheduler")
    public ResponseEntity<List<String>> getScheduledSting(@RequestParam(value = "time", defaultValue = "2022-05-12_23:11") String startTime) {
        List<String> scheduledList = cronSchedulerService.computeCron(startTime);
        return ResponseEntity.ok(scheduledList);
    }
}
