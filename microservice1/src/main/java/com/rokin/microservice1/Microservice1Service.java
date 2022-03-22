package com.rokin.microservice1;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class Microservice1Service {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    // Note: We can combine multiple resiliency patterns in a single method

    //    TODO: Add Fallback Method
    @CircuitBreaker(name = "microservice1")
    public String circuitBreakerMethod(String status) throws TimeoutException {
        if (FAIL.equals(status)) {
            throw new TimeoutException();
        }

        return "success";
    }

    //    TODO: Add Fallback Method
    @Bulkhead(name = "microservice1", type = Bulkhead.Type.SEMAPHORE)
    public String bulkheadMethod() throws InterruptedException {
        Thread.sleep(5000);
        return "success";
    }

    //    TODO: Add Fallback Method
    @Retry(name = "microservice1")
    public String retryMethod() throws TimeoutException {
        Random random = new Random();
        int num = random.nextInt();

        if (num < 0) {
            System.out.println("API failed");
            throw new TimeoutException();
        }

        System.out.println("API succeeded");

        return "success";
    }

    //    TODO: Add Fallback Method
    @RateLimiter(name = "microservice1")
    public String rateLimiterMethod() throws InterruptedException {
        return "success";
    }
}
