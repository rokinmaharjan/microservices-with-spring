package com.rokin.microservice2;


import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class Microservice2Service {
    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Map<String, Object> getInstances(String serviceId) {
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://microservice3/api/microservice3/instances")
//                .queryParam("serviceId", serviceId);
//
//        ResponseEntity<Map> restExchange = restTemplate.getForEntity(uriBuilder.build().toUri(), Map.class);
//
//        return restExchange.getBody();
//    }
//
//    public String sayHiToMicroservice3() {
//        ResponseEntity<String> restExchange =
//                restTemplate.exchange(
//                        "http://microservice3/api/microservice3",
//                        HttpMethod.GET,
//                        null, String.class);
//
//        return restExchange.getBody();
//    }

    public String propagateToken(String courseId) {
        ResponseEntity<String> restExchange =
                keycloakRestTemplate.exchange(
                        "http://localhost:8072/api/microservice3/microservice3/courses/" + courseId,
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }

    public String advise(String courseId) {
        if (courseId.equals("propagate")) {
            return String.format("Response after propagation: %s", propagateToken(courseId));
        }

        return courseId;
    }

}