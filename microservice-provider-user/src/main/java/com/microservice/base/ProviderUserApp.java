package com.microservice.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //标准客户端发现注解
//@EnableEurekaClient //只支持eureka客户端发现
public class ProviderUserApp {
	public static void main(String[] args) {
		SpringApplication.run(ProviderUserApp.class, args);
	}
}
