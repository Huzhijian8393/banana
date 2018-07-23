package com.huzj.eurekadiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekadiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekadiscoveryApplication.class, args);
    }
}
