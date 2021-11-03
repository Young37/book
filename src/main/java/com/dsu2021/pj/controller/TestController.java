package com.dsu2021.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String hello() {
		System.out.println("asdsad");
		return "hello";
	}
	
}
