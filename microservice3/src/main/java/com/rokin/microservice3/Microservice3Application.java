package com.rokin.microservice3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Microservice3Application {

    public static void main(String[] args) {
        SpringApplication.run(Microservice3Application.class, args);
    }

}
