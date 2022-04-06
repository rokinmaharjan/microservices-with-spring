package com.rokin.microservice3;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Microservice3Service {
    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;

    public String propagateToken(String courseId) throws Exception {
        try {
            ResponseEntity<String> restExchange =
                    keycloakRestTemplate.exchange(
                            "http://localhost:8072/api/microservice2/microservice2/validate/" + courseId,
                            HttpMethod.GET,
                            null, String.class);

            return restExchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteCourse(String courseId) throws Exception {
        if (courseId.equals("propagate")) {
            return String.format("Response after propagation: %s", propagateToken(courseId));
        }

        return courseId;
    }
}
