package com.rokin.microservice3;

import com.rokin.microservice3.events.source.CustomChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(CustomChannel.class)
@EnableDiscoveryClient
public class Microservice3Application {

    public static void main(String[] args) {
        SpringApplication.run(Microservice3Application.class, args);
    }

}
