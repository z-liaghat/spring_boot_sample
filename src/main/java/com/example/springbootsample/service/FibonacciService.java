package com.example.springbootsample.service;

public interface FibonacciService {
    long computeFibonacci(int id);
    long getCachedFibonacci(int id);
    public long[] getTwoBeforeNumberCachedFibonacci(int id);
}
