package com.rokin.microservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/microservice2")
public class Controller {

    @Value("${server.port}")
    private String port;

    @Autowired
    private BackedRestClient backedRestClient;

    @GetMapping
    public String sayHi() {
        return "Hi! from microservice2";
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        Map<String, Object> response = backedRestClient.getInstances(serviceId);
        response.put("microservice2", port);

        return response;
    }
}
