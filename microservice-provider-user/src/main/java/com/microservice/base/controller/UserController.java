package com.microservice.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;
import com.microservice.base.service.UserService;

@RestController
// 混合注解，相当于@ResponseBody+@Controller
// 即以下所有的请求都等价于@ResponseBody
public class UserController {
	private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userService.findUser(id);
	}

	@PostMapping("/user/post-add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	/**
	 * 这种方法，通过浏览器可以调用，但是通过RestTemplate和FeignClient都调用不到
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/user/get-add")
	public User addUser2(User user) {
		return userService.addUser(user);
	}

	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable("id") Long id) {
		return userService.findRole(id);
	}

	@PostMapping("/role/post-add")
	public Role addRole(@RequestBody Role role) {
		return userService.addRole(role);
	}

	@GetMapping("/complex1/{id}")
	public ComplexBean1 testComplexBean1(@PathVariable("id") Long id) {
		return userService.testComplex11(id);
	}

	@PostMapping("/complex1/post-add")
	public ComplexBean1 postAddComplexBean1(@RequestBody ComplexBean1 bean) {
		return userService.testComplex12(bean);
	}

	@GetMapping("/complex1/get-add")
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean) {
		return userService.testComplex12(bean);
	}

	@GetMapping("/complex2/{id}")
	public ComplexBean2 getComplexBean2(@PathVariable("id") Long id) {
		return userService.testComplex21(id);
	}

	@PostMapping("/complex2/post-add")
	public ComplexBean2 postAddComplexBean2(@RequestBody ComplexBean2 bean) {
		return userService.testComplex22(bean);
	}

	@GetMapping("/complex2/get-add")
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean) {
		return userService.testComplex22(bean);
	}

	/**
	 * 用于测试数据参数的传入和传出
	 * 
	 * @param ulist
	 * @return
	 */
	// @RequestMapping("/user-list")
	@PostMapping("/user-list")
	public List<User> testList(@RequestBody List<User> ulist) {
		if (CollectionUtils.isNotEmpty(ulist)) {
			for (User user : ulist) {
				// 测试类型是不是user
				LOG.info(user.toString());
			}
			return ulist;
		}
		ulist = new ArrayList<User>();
		ulist.add(userService.findUser(1L));
		ulist.add(userService.findUser(2L));
		LOG.info(ulist.toString());
		return ulist;

	}

	// @RequestMapping("/user-map")
	@PostMapping("/user-map")
	public Map<Long, User> testMap(@RequestBody Map<Long, User> umap) {
		if (MapUtils.isNotEmpty(umap)) {
			for (User user : umap.values()) {
				// 测试类型是不是user
				LOG.info(user.toString());
			}
			return umap;
		}
		umap = new HashMap<Long, User>();
		umap.put(1L, userService.findUser(1L));
		umap.put(2L, userService.findUser(2L));
		LOG.info(umap.toString());
		return umap;
	}

	// @RequestMapping("/user-set")
	@PostMapping("/user-set")
	public Set<User> testSet(@RequestBody Set<User> uset) {
		if (CollectionUtils.isNotEmpty(uset)) {
			for (User user : uset) {
				// 测试类型是不是user
				LOG.info(user.toString());
			}
			return uset;
		}
		uset = new HashSet<User>();
		uset.add(userService.findUser(1L));
		uset.add(userService.findUser(2L));
		LOG.info(uset.toString());
		return uset;
	}

	@GetMapping("/exception/{arg}")
	public String exception(@PathVariable("arg") String arg) throws Exception {
		throw new Exception(arg);
	}

	@GetMapping("/overtime/{second}")
	public String overtime(@PathVariable("second") Long second) throws Exception {
		Thread.sleep(second * 1000);
		return "Sleep " + second + " second OK.";
	}

}
