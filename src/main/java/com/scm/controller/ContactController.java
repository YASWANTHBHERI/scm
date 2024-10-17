package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;



@Controller
@RequestMapping("/user/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/add")
	public String addContactView(Model model){
		ContactForm contactForm = new ContactForm();
//		contactForm.setName("Yaswanth");
//		contactForm.setFavourite(true);
		model.addAttribute("contactForm",contactForm);
		
		return "user/add_contact";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String SaveContact(@ModelAttribute ContactForm contactForm, Authentication authentication) {
		System.out.println("Printing contact Form...............");
		System.out.println(contactForm);
		
		String userName = Helper.getEmailOfLoggedInUser(authentication);
		User user = userService.getUserByEmail(userName);
		
		Contact contact = new Contact();
		contact.setName(contactForm.getName());
		contact.setAddress(contactForm.getAddress());
		contact.setDescription(contactForm.getDescription());
		contact.setEmail(contactForm.getEmail());
		contact.setFavourite(contactForm.isFavourite());
		contact.setLinkedInLink(contactForm.getLinkedInLink());
		contact.setWebsiteLink(contactForm.getWebsiteLink());
		contact.setPhoneNumber(contactForm.getPhoneNumber());
		contact.setUser(user);
		
		contactService.save(contact);
		
		return "redirect:/user/contacts/add";
	}
	
	
	

}
