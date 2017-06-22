package com.microservice.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;
import com.microservice.base.repository.RoleRepository;
import com.microservice.base.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public User addUser(User user){
		LOG.info("in: "+user);
		User out = userRepository.save(user);
		LOG.info("out: "+out);
		return out;
	}
	
	public User findUser(Long id){
		LOG.info("in: "+id);
		User out = userRepository.findOne(id);
		LOG.info("out: "+out);
		return out;
	}
	
	public Role addRole(Role role){
		LOG.info("in: "+role);
		Role out = roleRepository.save(role);
		LOG.info("out: "+out);
		return out;
	}
	
	public Role findRole(Long id){
		LOG.info("in: "+id);
		Role out = roleRepository.findOne(id);
		LOG.info("out: "+out);
		return out;
	}
	
	public ComplexBean1 testComplex11(Long userId){
		LOG.info("in: "+ userId);
		User u = userRepository.findOne(userId);
		Role r = roleRepository.findOne(u.getRoleid());
		
		ComplexBean1 b = new ComplexBean1();
		b.setField(u.getId()+u.getName());
		b.setUser(u);
		b.setRole(r);
		LOG.info("out: "+b);
		return b;
	}
	
	public ComplexBean1 testComplex12(ComplexBean1 bean){
		LOG.info("in: "+ bean);
		bean.getField();
		return bean;
	}
	
	public ComplexBean2 testComplex21(Long userId){
		LOG.info("in: "+ userId);
		User u = userRepository.findOne(userId);
		Role r = roleRepository.findOne(u.getRoleid());
		
		ComplexBean2 b = new ComplexBean2();
		b.setField(u.getId()+u.getName());
		b.setUser(u);
		b.setRole(r);
		List<User> ulist = new ArrayList<User>();
		ulist.add(u);
		ulist.add(u);
		Map<Long, User> umap = new HashMap<Long, User>();
		umap.put(1L, u);
		umap.put(2L, u);
		b.setUserList(ulist);
		b.setUserMap(umap);
		List<Object> objList = new ArrayList<>();
		objList.add(u);
		objList.add(r);
		b.setObjList(objList);
		LOG.info("out: "+b);
		return b;
	}
	
	public ComplexBean2 testComplex22(ComplexBean2 bean){
		LOG.info("in: "+ bean);
		bean.getField();
		return bean;
	}
}
