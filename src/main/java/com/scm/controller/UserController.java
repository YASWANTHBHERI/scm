package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

//	user dashboard page
	@RequestMapping(value = "/dashboard")
	public String userDashBoard() {
		return "user/dashboard";
	}

//	user profile page
	@RequestMapping(value = "/profile")
	public String userProfile() {

		return "user/profile";
	}

}
