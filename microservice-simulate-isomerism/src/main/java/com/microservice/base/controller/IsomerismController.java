package com.microservice.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsomerismController {
	@GetMapping("/health.json")
	public Object health(){
		Map<String, String> r = new HashMap<>();
		r.put("status", "UP");
		return r;
	}
	
	@GetMapping("/info")
	public String info() {
		return "This is simulate isomerism.";
	}

}
