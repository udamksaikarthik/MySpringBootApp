package com.spring.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.UserDaoImpl;
import com.spring.demo.entity.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user= userDaoImpl.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: "+username));
		
		return user.map(MyUserDetails::new).get();
	}

}
