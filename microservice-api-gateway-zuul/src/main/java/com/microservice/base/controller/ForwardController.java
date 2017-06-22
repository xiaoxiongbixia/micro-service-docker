package com.microservice.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForwardController {
	
	@GetMapping("/locale1/forward1")
	public String localForward1(){
		return "this is local service /local1/forward1";
	}
	
	@GetMapping("/locale1/forward2")
	public String localForward2(){
		return "this is local service /local2/forward2";
	}
	
	@GetMapping("/other")
	public String other(){
		return "this is other url";
	}
	
	@GetMapping("/login")
	public String login(){
		return "this is login";
	}
	
	@GetMapping("/logout")
	public String logout(){
		return "this is logout";
	}
}
