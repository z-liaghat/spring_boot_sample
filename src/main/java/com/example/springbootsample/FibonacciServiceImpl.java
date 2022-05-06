package com.example.springbootsample;

public class FibonacciServiceImpl implements FibonacciService {

    @Override
    public long computeFibonacci(int id) {
//        Todo check if there is in the cache return it. or there is exist in db return it..
        
        if (id < 0) {
            return -1;
        }
        int fibonacciArrayLength = id + 1;
        final long[] fibo = new long[fibonacciArrayLength];
        fibo[0] = 0;
        if (fibo.length > 1) {
            fibo[1] = 1;
        }

        for (int index = 2; index < fibo.length; index++) {
            fibo[index] = fibo[index - 1] + fibo[index - 2];
        }
        return fibo[fibonacciArrayLength-1];
    }
}
