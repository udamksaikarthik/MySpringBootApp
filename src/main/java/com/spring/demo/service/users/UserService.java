package com.spring.demo.service.users;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.UserDao;
import com.spring.demo.entity.Users;

@Service
public class UserService implements UserServiceImpl{
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean addUser(Users user) {
		boolean error_flag = false;
		String errorMessage = "";
		System.out.println("UserService Logic");
			LocalDate todayDate = LocalDate.now();
			System.out.println("todayDate: "+todayDate);
			LocalDate dob = LocalDate.parse(user.getDateofbirth());
			System.out.println("DateOfBirth: "+dob);
			user.setAge(Period.between(dob, todayDate).getYears());
			user.setRoles("ROLE_USER");
			user.setActive(true);
			System.out.println(user.toString());
			userDao.addUser(user);
			return error_flag;
	}

	@Override
	public boolean checkUserInDB(String username) {
		return userDao.checkUserInDB(username);
	}

	@Override
	public boolean checkPasswordMismatch(@Valid Users user) {
		boolean error_flag = false;
		if(!user.getPassword().equals(user.getConfirmpassword())) {
			error_flag = true;
			return error_flag;
		}
		return error_flag;
	}

}
