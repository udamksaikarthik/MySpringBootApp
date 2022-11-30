package com.spring.demo.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.entity.Users;

@org.springframework.stereotype.Controller
public class Controller {
	
	@GetMapping("/")
	public ModelAndView home() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Home Method Starts");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Apps.html");
		System.out.println("Home Method Ends");
		System.out.println("---------------------------------------------------------------------------------");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("getLoginPage Method Starts");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
		System.out.println("authentication: "+authentication);
		}
		catch (Exception e) {
			System.out.println("Exception: "+e);
		}
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("Home.html");
			System.out.println("getLoginPage Method Ends");
			System.out.println("---------------------------------------------------------------------------------");
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("redirect:/");
			return mv;
		}
	}
	
	@GetMapping("/signUp")
	public ModelAndView getSignUpPage() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("getSignUpPage Method Starts");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SignUp.html");
		mv.addObject("user", new Users());
		System.out.println("getSignUpPage Method Ends");
		System.out.println("---------------------------------------------------------------------------------");
		return mv;
	}
	
}
