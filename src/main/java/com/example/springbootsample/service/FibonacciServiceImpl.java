package com.example.springbootsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
public class FibonacciServiceImpl implements FibonacciService {
    private final int cacheTtlInMinutes = 10;

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public long getCachedFibonacci(int id) {
        Long fibonacciNumber = redisTemplate.opsForValue().get(id + "");
        return Objects.requireNonNullElse(fibonacciNumber, (long) -1);
    }

    @Override
    public long[] getTwoBeforeNumberCachedFibonacci(int id) {
        Long onePreviousNumber =  redisTemplate.opsForValue().get(--id + "");
        Long twoPreviousNumber =  redisTemplate.opsForValue().get(--id + "");
        long[] result = new long[2];
        if (onePreviousNumber != null && twoPreviousNumber != null) {
            result[0] = twoPreviousNumber.longValue();
            result[1] = onePreviousNumber.longValue();
            return result;
        } else
            return null;

    }

    public void saveToCache(long[] fibonacciArray) {

        Duration ttlDuration = Duration.ofMinutes(cacheTtlInMinutes);
        Boolean isAbsent;
        for (int i = 0; i < fibonacciArray.length; i++) {
            isAbsent = redisTemplate.opsForValue().setIfAbsent(i + "", fibonacciArray[i], ttlDuration);
            if (isAbsent != null && !isAbsent) {
                redisTemplate.opsForValue().set(i + "", fibonacciArray[i], ttlDuration);
            }
        }

    }

    @Override
    public long computeFibonacci(int id) {
        long cachedFibonacci = getCachedFibonacci(id);
        if (cachedFibonacci != -1) {
            return cachedFibonacci;
        }
        long[] twoFibonacci = getTwoBeforeNumberCachedFibonacci(id);
        if (twoFibonacci != null) {
            return twoFibonacci[0] + twoFibonacci[1];
        }

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
        saveToCache(fibo);
        return fibo[fibonacciArrayLength - 1];
    }


}
