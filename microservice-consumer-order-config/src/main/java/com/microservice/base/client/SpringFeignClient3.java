package com.microservice.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.microservice.base.client.base.BasicSpringFeignClient;

@FeignClient(name="microservice-provider-user4", fallbackFactory = SpringFeignClient3FallbackFactory.class)
public interface SpringFeignClient3 extends BasicSpringFeignClient {
}

