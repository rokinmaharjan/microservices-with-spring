package com.rokin.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@RefreshScope
@EnableEurekaServer
public class ServiceDiscoveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryEurekaApplication.class, args);
    }

}
