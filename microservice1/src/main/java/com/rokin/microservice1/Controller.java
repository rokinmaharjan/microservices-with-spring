package com.rokin.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/microservice1")
public class Controller {

    @Value("${server.port}")
    private String port;

    @Autowired
    private Microservice1Service microservice1Service;

    @Autowired
    private Microservice2FeignClient microservice2FeignClient;

    @GetMapping
    public String sayHi() {
        return "Hi! from microservice1";
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        Map<String, Object> response = microservice2FeignClient.getInstances(serviceId);
        response.put("microservice1", port);

        return response;
    }

    @GetMapping("/circuit-breaker")
    public String demoCircuitBreaker(@RequestParam String status) throws TimeoutException {

        return microservice1Service.circuitBreakerMethod(status);
    }

    @GetMapping("/bulkhead")
    public String demoBulkhead() throws InterruptedException {
        return microservice1Service.bulkheadMethod();
    }

    @GetMapping("/retry")
    public String demoRetry() throws TimeoutException {
        return microservice1Service.retryMethod();
    }

    @GetMapping("/ratelimiter")
    public String demoRateLimiter() throws InterruptedException {
        return microservice1Service.rateLimiterMethod();
    }

}
