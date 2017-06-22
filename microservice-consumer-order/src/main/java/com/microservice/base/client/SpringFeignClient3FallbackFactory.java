package com.microservice.base.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SpringFeignClient3FallbackFactory implements FallbackFactory<SpringFeignClient3> {
	Logger LOG = LoggerFactory.getLogger(SpringFeignClient3FallbackFactory.class);
	
	@Override
	public SpringFeignClient3 create(Throwable arg0) {
		LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>DO SOMETHING>>>>>>>>>>>>>>>>>>>>>>>>");
		return new Fallback();
		
	}
	
	class Fallback extends SpringFeignClient2Fallback implements SpringFeignClient3 {
		public Fallback(){
			this.error = "fallback for spring feign client 3. ";
		}
	}
}
