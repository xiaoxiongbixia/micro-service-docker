package com.microservice.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;
import com.microservice.base.utils.RestTemplateUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

//没有指定resttemplate的配置
@RestController
@RequestMapping("/ribbon1")
public class RibbonController1 {
	private static final Logger LOG = LoggerFactory.getLogger(RibbonController1.class);

	@Autowired
	private RestTemplate restTemplate;

	// 该方法测试restTemplate的ribbon的负载均衡策略配置影响情况
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/balance")
	public String balanceTest() {
		ServiceInstance si1 = loadBalancerClient.choose("microservice-provider-user1");
		LOG.info(si1.getHost() + si1.getPort() + si1.getServiceId());

		ServiceInstance si2 = loadBalancerClient.choose("microservice-provider-user2");
		LOG.info(si2.getHost() + si2.getPort() + si2.getServiceId());

		return "OK";
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		User o = restTemplate.getForObject("http://microservice-provider-user1/user/" + id, User.class);
		return o;
	}

	@PostMapping("/user/post-add")
	public User addUser(@RequestBody User user) {
		User o = restTemplate.postForObject("http://microservice-provider-user1/user/post-add", user, User.class);
		return o;
	}

	/**
	 * 这种方法，通过浏览器可以调用，但是通过RestTemplate和FeignClient都调用不到
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/user/get-add")
	public User addUser2(User user) {
		User o = restTemplate.getForObject("http://microservice-provider-user1/user/get-add", User.class, user);
		return o;
	}

	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable("id") Long id) {
		Role o = restTemplate.getForObject("http://microservice-provider-user1/role/" + id, Role.class);
		return o;
	}

	@PostMapping("/role/post-add")
	public Role addRole(@RequestBody Role role) {
		Role o = restTemplate.postForObject("http://microservice-provider-user1/role/post-add", role, Role.class);
		return o;
	}

	@GetMapping("/complex1/{id}")
	public ComplexBean1 testComplexBean1(@PathVariable("id") Long id) {
		ComplexBean1 o = restTemplate.getForObject("http://microservice-provider-user1/complex1/" + id,
				ComplexBean1.class);
		return o;
	}

	@PostMapping("/complex1/post-add")
	public ComplexBean1 postAddComplexBean1(@RequestBody ComplexBean1 bean) {
		ComplexBean1 o = restTemplate.postForObject("http://microservice-provider-user1/complex1/post-add", bean,
				ComplexBean1.class);
		return o;
	}

	@GetMapping("/complex1/get-add")
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean) {
		ComplexBean1 o = restTemplate.getForObject("http://microservice-provider-user1/complex1/get-add",
				ComplexBean1.class, bean);
		return o;
	}

	@GetMapping("/complex2/{id}")
	public ComplexBean2 getComplexBean2(@PathVariable("id") Long id) {
		ComplexBean2 o = restTemplate.getForObject("http://microservice-provider-user1/complex2/" + id,
				ComplexBean2.class);
		return o;
	}

	@PostMapping("/complex2/post-add")
	@HystrixCommand(fallbackMethod = "complex2Fallback")
	public ComplexBean2 postAddComplexBean2(@RequestBody ComplexBean2 bean) {
		ComplexBean2 o = restTemplate.postForObject("http://microservice-provider-user1/complex2/post-add", bean,
				ComplexBean2.class);
		return o;
	}

	@GetMapping("/complex2/get-add")
	@HystrixCommand(fallbackMethod = "complex2Fallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
	})
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean) {
		ComplexBean2 o = restTemplate.getForObject("http://microservice-provider-user1/complex2/get-add",
				ComplexBean2.class, bean);
		return o;
	}

	/**
	 * 证明了底层调用GET方法是不能传入复杂对象的
	 * @param bean
	 * @return
	 */
	@PostMapping("/complex2/post-add2get")
	@HystrixCommand(fallbackMethod = "complex2Fallback")
	public ComplexBean2 postAddTestComplexBean2(ComplexBean2 bean) {
		ComplexBean2 o = restTemplate.getForObject("http://microservice-provider-user1/complex2/get-add",
				ComplexBean2.class, bean);
		return o;
	}
	
	public ComplexBean2 complex2Fallback(ComplexBean2 bean){
		ComplexBean2 o = new ComplexBean2();
		o.setField("error , this is hystrix");
		return o;
	}

	/**
	 * 用于测试数据参数的传入和传出；
	 * 传出因为没办法明确指定诸如List<User>这种类型，所以是没法直接翻译成这种类型的
	 * 
	 * @param ulist
	 * @return
	 */
	@PostMapping("/user-list")
	public List<User> testList(@RequestBody List<User> ulist) {
		System.out.println(ulist.size());
		List<User> o = RestTemplateUtils.getForListObject(
				restTemplate.postForObject("http://microservice-provider-user1/user-list", ulist, User[].class));
		System.out.println(o.size());
		return o;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/user-map")
	public Map<Long, User> testMap(@RequestBody Map<Long, User> umap) {
		Object o = restTemplate.postForObject("http://microservice-provider-user1/user-map", umap, Map.class);
		return (Map<Long, User>) o;
	}

	@PostMapping("/user-set")
	public Set<User> testSet(@RequestBody Set<User> uset) {
		//这句代码返回的事Set<Map>格式的对象，不能翻译成user
		//Object o = restTemplate.postForObject("http://microservice-provider-user1/user-set",  uset, Set.class);
		Set<User> o = RestTemplateUtils.getForSetObject(restTemplate.postForObject("http://microservice-provider-user1/user-set",  uset, User[].class));
		return (Set<User>) o;
	}
	
	
	
	/**
	 * 用于测试数据参数的传入和传出, 通过ResponseEntity
	 * 还是不能成功将数据翻译成想要的类型，当然RestTemplate的配置是很复杂的，没准哪种就可以了
	 * 
	 * @param ulist
	 * @return
	 */
	@PostMapping("/entity/user-list")
	public List<User> testListEntity(@RequestBody List<User> ulist) {
		List<User> o = new ArrayList<>();
		@SuppressWarnings("unchecked")
		ResponseEntity<List<User>> u = (ResponseEntity<List<User>>) restTemplate.postForEntity("http://microservice-provider-user1/user-list", ulist, o.getClass());
		o = u.getBody();
		return o;
	}

	@PostMapping("/entity/user-map")
	public Map<Long, User> testMapEntity(@RequestBody Map<Long, User> umap) {
		Map<Long, User> o = new HashMap<>();
		@SuppressWarnings("unchecked")
		ResponseEntity<Map<Long, User>> u = (ResponseEntity<Map<Long, User>>) restTemplate.postForEntity("http://microservice-provider-user1/user-map", umap, o.getClass());
		o = u.getBody();
		return o;
	}

	@PostMapping("/entity/user-set")
	public Set<User> testSetEntity(@RequestBody Set<User> uset) {
		Set<User> o = new HashSet<>();
		@SuppressWarnings("unchecked")
		ResponseEntity<Set<User>> u = (ResponseEntity<Set<User>>) restTemplate.postForEntity("http://microservice-provider-user1/user-set", uset, o.getClass());
		o = u.getBody();
		return o;
	}
	
	/**
	 * 以下两个方法证明，没有断路器的方法，会一直执行到方法结束，无论是成功或是失败
	 * 注解了断路器的方法，会有一个默认的超时时间，大概是3秒左右，超时了，无论是成功或者失败都走断路器，断路器5秒20次后会打开，但是打开了过一小段时间就会尝试关闭，如果一测试还是不好的，就又打开。
	 * @param second
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/without-hystrix/{second}")
	public String testWithoutHystrix(@PathVariable int second) throws Exception{
		Thread.sleep(second * 1000);
		if(second%2==0){
			return "OK"+second;
		}
		throw new Exception();
	}
	
	@GetMapping("/with-hystrix/{second}")
	@HystrixCommand(fallbackMethod = "defaultFallback")
	public String testWithHystrix(@PathVariable int second) throws Exception{
		Thread.sleep(second * 1000);
		if(second%2==0){
			return "OK"+second;
		}
		throw new Exception();
	}
	
	public String defaultFallback(int arg) {
		return "error , this is hystrix " + arg;
	}
	
	@GetMapping("/exception/{arg}")
	@HystrixCommand(fallbackMethod = "exceptionFallback")
	public String exception(@PathVariable String arg){
		return restTemplate.getForObject("http://microservice-provider-user1/exception/"+arg, String.class);
	}
	/**
	 * 注意，断路器的输入输出参数都要和原方法保持一致哦
	 * @param arg
	 * @return
	 */
	public String exceptionFallback(String arg){
		return "error , this is exception " + arg;
	}
	
	@GetMapping("/overtime/{second}")
	@HystrixCommand(fallbackMethod = "overtimeFallback")
	public String overtime(@PathVariable Long second){
		return restTemplate.getForObject("http://microservice-provider-user1/overtime/"+second, String.class);
	}
	public String overtimeFallback(Long second){
		return "error, this is overtime. "+second;
	}
}
