package com.microservice.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.base.client.SpringFeignClient1;
import com.microservice.base.client.base.BasicSpringFeignClient;
import com.microservice.base.controller.base.AbstractSpringFeignController;

@RestController
@RequestMapping("/spring-feign1")
public class SpringFeignController1 extends AbstractSpringFeignController {

	@Autowired
	private SpringFeignClient1 client;

	@Override
	public BasicSpringFeignClient client() {
		return this.client;
	}
}
