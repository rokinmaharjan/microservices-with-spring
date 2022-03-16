package com.rokin.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/microservice1")
public class Controller {

    @Value("${server.port}")
    private String port;

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
}
