package com.garage.idm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IdmApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdmApplication.class, args);
    }
}
