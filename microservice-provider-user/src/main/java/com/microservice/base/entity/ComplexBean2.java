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
	/**
	 * objList这种属性，在http翻译的过程中，只会变成List<LinkedHashMap>
	 * 因为接收端，不知道其具体类型到底是什么
	 */
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
