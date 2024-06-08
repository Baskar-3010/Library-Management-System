package com.lms.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String getUser() {
		return "hi bas User";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdmin() {
		return "hi bas Admin";
	}
}
