package com.microservice.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.base.client.SpringFeignClient2;
import com.microservice.base.client.base.BasicSpringFeignClient;
import com.microservice.base.controller.base.AbstractSpringFeignController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 测试Feign已经配置好了断路器，如果又结合HystrixCommand会怎么样
 * 事实证明，会走HystrixCommand方法
 */
@RestController
@RequestMapping("/spring-feign4")
public class SpringFeignController4 extends AbstractSpringFeignController {

	@Autowired
	private SpringFeignClient2 client;

	@Override
	public BasicSpringFeignClient client() {
		return this.client;
	}
	
	@Override
	@HystrixCommand(fallbackMethod="overtimeFallback")
	public String overtime(@PathVariable Long second) {
		return client().overtime(second);
	}
	
	public String overtimeFallback(Long second){
		return "error feign, this is overtime. "+second;
	}
}
