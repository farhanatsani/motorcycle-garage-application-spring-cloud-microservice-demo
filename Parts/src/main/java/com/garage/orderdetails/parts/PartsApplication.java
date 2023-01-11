package com.garage.orderdetails.parts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PartsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PartsApplication.class, args);
    }
}
