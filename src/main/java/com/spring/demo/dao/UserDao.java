package com.spring.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.demo.entity.Users;

@Repository
public class UserDao{
	
	@Autowired
	private UserDaoImpl userDaoimpl;
	
	public void addUser(Users user) {
		userDaoimpl.save(user);
	}

	public boolean checkUserInDB(String username) {
		boolean flag = false;
		List<Users> user = userDaoimpl.FetchTheUserDetailsByUsername(username);
		if(user.size()!=0) {
			flag = true;
		}
		return flag;
	}
	
}
