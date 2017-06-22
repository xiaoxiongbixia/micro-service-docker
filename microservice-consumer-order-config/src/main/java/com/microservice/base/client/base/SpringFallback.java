package com.microservice.base.client.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;

public class SpringFallback implements BasicSpringFeignClient{

	public String error = "fallback for spring feign client. ";
	
	@Override
	public User getUser(Long id) {
		User u = new User();
		u.setUsername(error);
		return u;
	}

	@Override
	public User addUser(User user) {
		User u = new User();
		u.setUsername(error);
		return u;
	}

	@Override
	public User addUser2(User user) {
		User u = new User();
		u.setUsername(error);
		return u;
	}

	@Override
	public Role getRole(Long id) {
		Role r = new Role();
		r.setDescription(error);
		return r;
	}

	@Override
	public Role addRole(Role role) {
		Role r = new Role();
		r.setDescription(error);
		return r;
	}

	@Override
	public ComplexBean1 testComplexBean1(Long id) {
		ComplexBean1 b = new ComplexBean1();
		b.setField(error);
		return b;
	}

	@Override
	public ComplexBean1 postAddComplexBean1(ComplexBean1 bean) {
		ComplexBean1 b = new ComplexBean1();
		b.setField(error);
		return b;
	}

	@Override
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean) {
		ComplexBean1 b = new ComplexBean1();
		b.setField(error);
		return b;
	}

	@Override
	public ComplexBean2 getComplexBean2(Long id) {
		ComplexBean2 b = new ComplexBean2();
		b.setField(error);
		return b;
	}

	@Override
	public ComplexBean2 postAddComplexBean2(ComplexBean2 bean) {
		ComplexBean2 b = new ComplexBean2();
		b.setField(error);
		return b;
	}

	@Override
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean) {
		ComplexBean2 b = new ComplexBean2();
		b.setField(error);
		return b;
	}

	@Override
	public List<User> testList(List<User> ulist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, User> testMap(Map<Long, User> umap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> testSet(Set<User> uset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String exception(String arg){
		return error + arg;
	}

	@Override
	public String overtime(Long second){
		return error + second;
	}

}
