package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

	public static String getEmailOfLoggedInUser(Authentication authentication) {

		if (authentication instanceof OAuth2AuthenticationToken) {

//			Google Login checking

			var aoAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			var clientId = aoAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

			var oauth2User = (OAuth2User) authentication.getPrincipal();

			String userName = "";

			if (clientId.equalsIgnoreCase("google")) {
				userName = oauth2User.getAttribute("email").toString();
				System.out.println("getting google email from helper class: " + userName);

			} else if (clientId.equalsIgnoreCase("github")) {

				userName = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
						: (oauth2User.getAttribute("login").toString() + "@gmail.com").toLowerCase();

				System.out.println("getting github email from helper class: " + userName);

			}

			return userName;
		} else {
			return authentication.getName();
		}

//		normal user

	}

}
