package com.microservice.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.microservice.base.client.base.BasicDefaultFeignClient;
import com.microservice.base.config.FeignConfiguration;

@FeignClient(name="microservice-provider-user2", configuration=FeignConfiguration.class)
public interface DefaultFeignClient1 extends BasicDefaultFeignClient {
}
