package com.microservice.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.microservice.base.client.base.BasicSpringFeignClient;

@FeignClient(name="microservice-provider-user3", fallback = SpringFeignClient2Fallback.class)
public interface SpringFeignClient2 extends BasicSpringFeignClient {
	
}

