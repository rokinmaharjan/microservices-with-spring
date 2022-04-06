package com.rokin.microservice3;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RestController
@RequestMapping(value = "/microservice3")
public class Controller {

    @Autowired
    private Microservice3Service microservice3Service;


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

    @RolesAllowed({"PROFESSOR"})
    @GetMapping("/courses/{courseId}")
    public String getCourse(@PathVariable String courseId) {
        return courseId;
    }

    @GetMapping("/policies")
    public String getPolicies() {
        return "Policies";
    }

    @RolesAllowed({"PROFESSOR", "ADMIN"})
    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable String courseId) throws Exception{
        return microservice3Service.deleteCourse(courseId);
    }

}
