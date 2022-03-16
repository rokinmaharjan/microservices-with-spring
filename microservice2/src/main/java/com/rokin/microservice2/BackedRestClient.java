package com.rokin.microservice2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class BackedRestClient {
    @Autowired
    RestTemplate restTemplate;

    public Map<String, Object> getInstances(String serviceId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://microservice3/microservice3/instances")
                .queryParam("serviceId", serviceId);

        ResponseEntity<Map> restExchange = restTemplate.getForEntity(uriBuilder.build().toUri(), Map.class);

        return restExchange.getBody();
    }
}