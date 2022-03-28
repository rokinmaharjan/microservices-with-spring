package com.rokin.microservice3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/microservice3")
public class Controller {

    @Autowired
    private SpringDiscoveryClient discoveryClient;

    @GetMapping
    public String sayHi(@RequestHeader("custom-header") String customHeader) {
        return String.format("Hi from microservice3 with custom-header %s", customHeader);
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

}
