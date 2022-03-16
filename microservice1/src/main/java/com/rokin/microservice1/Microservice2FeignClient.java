package com.rokin.microservice1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("microservice2")
public interface Microservice2FeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/microservice2/instances",
            consumes = "application/json")
    Map<String, Object> getInstances(@RequestParam("serviceId") String serviceId);

}
