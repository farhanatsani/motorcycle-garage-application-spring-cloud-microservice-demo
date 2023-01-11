package com.garage.mechanics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MechanicApplication {
    public static void main(String[] args) {
        SpringApplication.run(MechanicApplication.class, args);
    }
}
