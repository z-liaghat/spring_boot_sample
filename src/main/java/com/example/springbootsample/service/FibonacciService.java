package com.example.springbootsample.service;

public interface FibonacciService {
    String  computeFibonacci(int id);
    long getCachedFibonacci(int id);
    public long[] getTwoBeforeNumberCachedFibonacci(int id);
}
