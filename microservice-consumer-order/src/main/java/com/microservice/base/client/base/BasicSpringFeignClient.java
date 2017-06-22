package com.microservice.base.client.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;

/**
 * 老版本的feign是不支持以下的Spring的混合注解的，新版本已经支持
 * @author Administrator
 *
 */
public interface BasicSpringFeignClient {
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id);

	@PostMapping("/user/post-add")
	public User addUser(@RequestBody User user);

	@GetMapping("/user/get-add")
	public User addUser2(User user);

	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable("id") Long id);

	@PostMapping("/role/post-add")
	public Role addRole(@RequestBody Role role);

	@GetMapping("/complex1/{id}")
	public ComplexBean1 testComplexBean1(@PathVariable("id") Long id);

	@PostMapping("/complex1/post-add")
	public ComplexBean1 postAddComplexBean1(@RequestBody ComplexBean1 bean);

	@GetMapping("/complex1/get-add")
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean);

	@GetMapping("/complex2/{id}")
	public ComplexBean2 getComplexBean2(@PathVariable("id") Long id);

	@PostMapping("/complex2/post-add")
	public ComplexBean2 postAddComplexBean2(@RequestBody ComplexBean2 bean);

	@GetMapping("/complex2/get-add")
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean);
	
	@PostMapping("/user-list")
	public List<User> testList(@RequestBody List<User> ulist);
	@PostMapping("/user-map")
	public Map<Long, User> testMap(@RequestBody Map<Long, User> umap);
	
	@PostMapping("/user-set")
	public Set<User> testSet(@RequestBody Set<User> uset);

	@GetMapping("/exception/{arg}")
	public String exception(@PathVariable("arg") String arg);

	@GetMapping("/overtime/{second}")
	public String overtime(@PathVariable("second") Long second);
}
