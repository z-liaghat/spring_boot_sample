package com.example.springbootsample.service;

import java.util.List;

public interface CronSchedulerService {
    List<String> computeCron(String startTimestamp);
}
