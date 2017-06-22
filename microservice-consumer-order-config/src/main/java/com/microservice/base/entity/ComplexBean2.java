package com.microservice.base.entity;

import java.util.List;
import java.util.Map;

public class ComplexBean2 extends ComplexBean1{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> userList;
	
	private Map<Long, User> userMap;
	
	private List<Object> objList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Map<Long, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<Long, User> userMap) {
		this.userMap = userMap;
	}

	
	public List<Object> getObjList() {
		return objList;
	}

	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}

	@Override
	public String toString() {
		return "ComplexBean2 [userList=" + userList + ", userMap=" + userMap + ", objList=" + objList + "]";
	}
}
