package com.epam.pratsko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestRestController {

    private final LoadBalancerClient loadBalancerClient;

    @GetMapping("/test_song_service")
    public ServiceInstance getSongServiceInstance() {
        return loadBalancerClient.choose("song-service");
    }

}
