package com.rokin.microservice2;

import com.rokin.microservice2.usercontext.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Microservice2Service microservice2Service;

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    @GetMapping
    public String sayHi() {
        return "Hi! from microservice2";
    }

    @GetMapping("/instances")
    public Map<String, Object> getInstances(@RequestParam String serviceId) {
        Map<String, Object> response = microservice2Service.getInstances(serviceId);
        response.put("microservice2", port);

        return response;
    }

    @GetMapping("/custom-header")
    public String testCustomHeaderViaUserContext() {
        logger.debug("Microservice2 custom-header: {}", UserContextHolder.getContext().getCustomHeader());
        return microservice2Service.sayHiToMicroservice3();
    }
}
