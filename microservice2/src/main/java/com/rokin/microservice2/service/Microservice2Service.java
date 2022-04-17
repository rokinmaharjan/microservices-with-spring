package com.rokin.microservice2.service;


import com.rokin.microservice2.model.Organization;
import com.rokin.microservice2.repository.RedisOrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class Microservice2Service {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RedisOrganizationRepository organizationRepository;

    private static final Logger logger = LoggerFactory.getLogger(Microservice2Service.class);

    public Map<String, Object> getInstances(String serviceId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://microservice3/microservice3/instances")
                .queryParam("serviceId", serviceId);

        ResponseEntity<Map> restExchange = restTemplate.getForEntity(uriBuilder.build().toUri(), Map.class);

        return restExchange.getBody();
    }

    public String sayHiToMicroservice3() {
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://microservice3/microservice3",
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }

    private Organization checkRedisForOrganization(String organizationId) {
        try {
            return organizationRepository.findById(organizationId).orElse(null);
        } catch (Exception e) {
            logger.error("Error occured while fetching organization '{}' from redis. Exception: {}", organizationId, e);
            return null;
        }
    }

    private void cacheOrganization(Organization organization) {
        try {
            organizationRepository.save(organization);
        } catch (Exception e) {
            logger.error("Error occurred while caching organization '{}'. Exception: {}", organization.getId(), e);
        }
    }

    public Organization getOrganization(String organizationId) {
        Organization organization = checkRedisForOrganization(organizationId);

        if (organization != null) {
            logger.info("Fetched organization '{}' from Redis.", organizationId);
        } else {
            logger.info("Organization '{}' not found in Redis. Now fetching with API call.", organizationId);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://microservice3/microservice3/organizations/" + organizationId);
            ResponseEntity<Organization> restExchange = restTemplate.getForEntity(uriBuilder.build().toUri(), Organization.class);
            organization = restExchange.getBody();

            cacheOrganization(organization);
        }

        return organization;
    }
}