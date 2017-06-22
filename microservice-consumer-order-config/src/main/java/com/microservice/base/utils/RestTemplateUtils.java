package com.microservice.base.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestTemplateUtils {
	public static <T> List<T> getForListObject(T[] array){
		return Arrays.asList(array);
	}
	
	public static <T> Set<T> getForSetObject(T[] array){
		Set<T> set = new HashSet<T>();
		for (T t : array) {
			set.add(t);
		}
		return set;
	}

}
