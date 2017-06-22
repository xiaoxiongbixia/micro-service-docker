package com.microservice.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.microservice.base.config.RibbonConfiguration;
/**
 * SpringBoot启动类注解
 */
@SpringBootApplication
/**
 * 客户端发现注解，表示可以向注册中心注册，也可以是@EnableEurekaClient
 * 不过@EnableEurekaClient只能在eurek中使用, 而@EnableDiscoveryClient比较通用
 */
@EnableDiscoveryClient
/**
 * 引入FeignClient组件，表示其他标记@FeignClient的类可以使用
 */
@EnableFeignClients
/**
 * Ribbon客户端自定义配置
 */
@RibbonClient(name = "microservice-provider-user1", configuration=RibbonConfiguration.class)
/**
 * 引入断路器组件，断路器组件必须依赖<artifactId>spring-boot-starter-actuator</artifactId>和<artifactId>spring-cloud-starter-hystrix</artifactId>两个包
 */
@EnableCircuitBreaker
/**
 * 包扫描，定义排除某些包，过滤方法可以参考XML文件的方式，有很多种的
 */
@ComponentScan(excludeFilters={@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.microservice.base.config.*")})
public class ConfigConsumerOrderApp {
	
	@LoadBalanced //该注解就是客户端负载均衡。。。。表示这个客户端会走负载策略
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigConsumerOrderApp.class, args);
	}
}
