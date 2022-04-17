package com.rokin.microservice3.controller;

import com.rokin.microservice3.SpringDiscoveryClient;
import com.rokin.microservice3.model.Organization;
import com.rokin.microservice3.service.Microservice3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/microservice3")
public class Controller {

    @Autowired
    private SpringDiscoveryClient discoveryClient;

    @Autowired
    private Microservice3Service microservice3Service;

    @GetMapping
    public String sayHi(@RequestHeader("custom-header") String customHeader) {
        return String.format("Hi from microservice3 with custom-header %s", customHeader);
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    @GetMapping("/notify-kafka")
    public String notifyKafka() {
        return microservice3Service.publishToKafka();
    }

    @GetMapping("/organizations/{id}")
    public Organization getOrganizationById(@PathVariable String id) {
        return microservice3Service.getOrganizationById(id);
    }

    @PutMapping("/organizations/{id}")
    public Organization getOrganizationById(@PathVariable String id, @RequestBody Organization organization) {
        return microservice3Service.updateOrganization(id, organization);
    }

}
