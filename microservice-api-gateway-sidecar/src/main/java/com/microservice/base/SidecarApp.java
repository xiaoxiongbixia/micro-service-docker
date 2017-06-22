package com.microservice.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class SidecarApp {
	public static void main(String[] args) {
		SpringApplication.run(SidecarApp.class, args);
	}
}
