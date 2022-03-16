package com.rokin.microservice3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SpringDiscoveryClient {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    public Map<String, Object> getInstances(String serviceId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        if (instances.size() == 0) return null;

        List<String> foundInstances = new ArrayList<>();
        for (ServiceInstance instance : instances) {
            foundInstances.add(instance.getServiceId() + ": " + instance.getInstanceId());
        }

//        Use a DTO instead. This is only for the purpose of this demo
        Map<String, Object> response = new HashMap<>();
        response.put("instances", foundInstances);
        response.put("microservice3", port);

        return response;
    }
}
