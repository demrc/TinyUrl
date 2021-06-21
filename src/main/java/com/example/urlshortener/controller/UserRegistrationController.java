package com.example.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.urlshortener.domain.UserDto;
import com.example.urlshortener.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
		
	@ModelAttribute("user")
	public UserDto userRegistrationDto() {
		return new UserDto();
	}
		
	@GetMapping
	public String showRegistrationForm() {
		return "/registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}