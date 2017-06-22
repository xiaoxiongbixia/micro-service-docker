package com.microservice.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class RibbonConfiguration {
	@Autowired
	private IClientConfig clientConfig;
	
	@Bean
	public IRule ribbonRule(IClientConfig clientConfig){
		return new RandomRule();
	}
}
