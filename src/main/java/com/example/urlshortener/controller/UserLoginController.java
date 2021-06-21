package com.example.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.urlshortener.domain.UserDto;
import com.example.urlshortener.model.User;
import com.example.urlshortener.service.UserService;

@Controller
@RequestMapping("/login")
public class UserLoginController {

	private UserService userService;

	public UserLoginController(UserService userService) {
		super();
		this.userService = userService;
	}
		
	@ModelAttribute("user")
	public UserDto userLoginDto() {
		return new UserDto();
	}
		
	@GetMapping
	public String showLoginForm() {
		return "/login";
	}
	
	@PostMapping
	public ModelAndView loginUserAccount(HttpServletRequest request,@ModelAttribute("user") UserDto loginDto, 
			ModelMap model,RedirectAttributes att) {
	    User user= userService.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
	    if(user == null) {
	    	ModelAndView modelAndView = new ModelAndView("login?error"); 
	        return modelAndView;
		} else {
			               
	        att.addFlashAttribute("id",user.getId());//redirect ederken data geçmeye yarayan metod.
	       
	         return new ModelAndView("redirect:/index");
		}
	}
}