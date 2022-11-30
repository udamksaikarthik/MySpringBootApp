package com.spring.demo.service.users;

import javax.validation.Valid;

import com.spring.demo.entity.Users;

public interface UserServiceImpl {

	boolean addUser(Users user);

	boolean checkUserInDB(String username);

	boolean checkPasswordMismatch(@Valid Users user);

}
