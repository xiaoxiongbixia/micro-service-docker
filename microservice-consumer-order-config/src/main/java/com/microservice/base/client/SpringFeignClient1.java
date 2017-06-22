package com.microservice.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.microservice.base.client.base.BasicSpringFeignClient;

//这个是默认配置的Feign
@FeignClient("microservice-provider-user1")
public interface SpringFeignClient1 extends BasicSpringFeignClient {
	
}
