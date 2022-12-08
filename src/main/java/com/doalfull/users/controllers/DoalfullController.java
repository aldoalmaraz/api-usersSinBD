package com.doalfull.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")

public class DoalfullController {
	
	@GetMapping
	public String helloWorld() {
		return "hello world";
	}

}
