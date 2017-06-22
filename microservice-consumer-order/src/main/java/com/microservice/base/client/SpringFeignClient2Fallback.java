package com.microservice.base.client;


import org.springframework.stereotype.Component;

import com.microservice.base.client.base.SpringFallback;

@Component
public class SpringFeignClient2Fallback extends SpringFallback implements SpringFeignClient2 {
	public SpringFeignClient2Fallback(){
		this.error = "fallback for spring feign client 2. ";
	}
}
