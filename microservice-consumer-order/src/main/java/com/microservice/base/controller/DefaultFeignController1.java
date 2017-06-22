package com.microservice.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.base.client.DefaultFeignClient1;
import com.microservice.base.client.base.BasicDefaultFeignClient;
import com.microservice.base.controller.base.AbstractDefaultFeignController;

//事实证明最新版本的fegin也支持GetMapping这种组合注解了
@RestController
@RequestMapping("/default-feign1")
public class DefaultFeignController1 extends AbstractDefaultFeignController {

	@Autowired
	private DefaultFeignClient1 client;

	@Override
	public BasicDefaultFeignClient client() {
		return this.client;
	}
}
