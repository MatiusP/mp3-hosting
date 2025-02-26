package com.epam.pratsko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResourceService {
    public static void main(String[] args) {
        SpringApplication.run(ResourceService.class, args);
    }
}