package com.spring.demo.controller.users;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.entity.Users;
import com.spring.demo.service.users.UserServiceImpl;

@org.springframework.stereotype.Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/addUser")
	public ModelAndView addUser(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("addUser Method Starts");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SignUp.html");
		if(bindingResult.hasErrors()) {
			return mv;
		}else {
			boolean error_flag = userServiceImpl.checkPasswordMismatch(user);
			boolean existing_user_name_flag = userServiceImpl.checkUserInDB(user.getUsername());
			if(error_flag && existing_user_name_flag) {
				String confirm_password_error_message = "Password & Confirm Password doesn't Match!";
				System.out.println("confirm_password_error_message: "+confirm_password_error_message);
				mv.addObject("confirm_password_error_message", confirm_password_error_message);
				String existing_user_name_error_message = "Username is already Taken!";
				System.out.println("existing_user_name_error_message: "+existing_user_name_error_message);
				mv.addObject("existing_user_name_error_message", existing_user_name_error_message);
				boolean password_error_display_flag = true;
				mv.addObject("password_error_display_flag", password_error_display_flag);
				System.out.println("addUser Method Ends");
				System.out.println("---------------------------------------------------------------------------------");
				return mv;
			}
			else if(error_flag) {
				String confirm_password_error_message = "Password & Confirm Password doesn't Match!";
				System.out.println("confirm_password_error_message: "+confirm_password_error_message);
				mv.addObject("confirm_password_error_message", confirm_password_error_message);
				boolean password_error_display_flag = true;
				mv.addObject("password_error_display_flag", password_error_display_flag);
				System.out.println("addUser Method Ends");
				System.out.println("---------------------------------------------------------------------------------");
				return mv;
			}
			else if(existing_user_name_flag){
				String existing_user_name_error_message = "Username is already Taken!";
				System.out.println("existing_user_name_error_message: "+existing_user_name_error_message);
				mv.addObject("existing_user_name_error_message", existing_user_name_error_message);
				boolean password_error_display_flag = true;
				mv.addObject("password_error_display_flag", password_error_display_flag);
				System.out.println("addUser Method Ends");
				System.out.println("---------------------------------------------------------------------------------");
				return mv;
			}
			else
				{
				userServiceImpl.addUser(user);
				String no_error_message = "User Added Successfully!";
				System.out.println("no_error_message: "+no_error_message);
				mv.addObject("no_error_message", no_error_message);
				boolean success_display_flag = true;
				mv.setViewName("Home.html");
				mv.addObject("success_display_flag", success_display_flag);
				System.out.println("addUser Method Ends");
				System.out.println("---------------------------------------------------------------------------------");
				return mv;
			}
		}
	}
	
	@PostMapping("/login")
	public ModelAndView LoginUser() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("LoginUser Method");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Apps.html");
		return mv;
	}
	
}
