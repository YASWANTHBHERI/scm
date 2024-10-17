package com.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@ModelAttribute
	public void addLoggedInUserInformation(Model model, Authentication authentication) {
		
		if(authentication==null) {
			return;
		}
		
		logger.info("Adding logged in user info to the model");
		String email = Helper.getEmailOfLoggedInUser(authentication);
		logger.info("User logged in: " + email);
		
		User user = userService.getUserByEmail(email);
		logger.info(user.getName());
		logger.info(user.getEmail());
		model.addAttribute("loggedInUser", user);
	}
	
	
}
