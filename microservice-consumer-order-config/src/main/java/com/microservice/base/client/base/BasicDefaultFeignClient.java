package com.microservice.base.client.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;

import feign.RequestLine;
/**
 * 由于测试的缘故，需要好几套客户端代码，所以先抽取几个基本代码
 * @author Administrator
 *
 */
public interface BasicDefaultFeignClient {
	@RequestLine("GET /user/{id}")
	public User getUser(@PathVariable("id") Long id);

	@RequestLine("POST /user/post-add")
	public User addUser(  User user);

	@RequestLine("GET /user/get-add")
	public User addUser2(User user);

	@RequestLine("GET /role/{id}")
	public Role getRole(@PathVariable("id") Long id);

	@RequestLine("POST /role/post-add")
	public Role addRole(  Role role);

	@RequestLine("GET /complex1/{id}")
	public ComplexBean1 testComplexBean1(@PathVariable("id") Long id);

	@RequestLine("POST /complex1/post-add")
	public ComplexBean1 postAddComplexBean1(  ComplexBean1 bean);

	@RequestLine("GET /complex1/get-add")
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean);

	@RequestLine("GET /complex2/{id}")
	public ComplexBean2 getComplexBean2(@PathVariable("id") Long id);

	@RequestLine("POST /complex2/post-add")
	public ComplexBean2 postAddComplexBean2(  ComplexBean2 bean);

	@RequestLine("GET /complex2/get-add")
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean);
	
	@RequestLine("POST /user-list")
	public List<User> testList(  List<User> ulist);
	@RequestLine("POST /user-map")
	public Map<Long, User> testMap(  Map<Long, User> umap);
	
	@RequestLine("POST /user-set")
	public Set<User> testSet(  Set<User> uset);

	@RequestLine("GET /exception/{arg}")
	public String exception(@PathVariable("arg") String arg);

	@RequestLine("GET /overtime/{second}")
	public String overtime(@PathVariable("second") Long second);
}
