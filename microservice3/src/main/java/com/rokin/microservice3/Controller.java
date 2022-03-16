package com.rokin.microservice3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/microservice3")
public class Controller {

    @Autowired
    private SpringDiscoveryClient discoveryClient;

    @GetMapping
    public String sayHi() {
        return "Hi! from microservice3";
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

}
