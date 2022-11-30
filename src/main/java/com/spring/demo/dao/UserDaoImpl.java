package com.spring.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.demo.entity.Users;

public interface UserDaoImpl extends JpaRepository<Users, Long>{

	Optional<Users> findByUsername(String username);
	
	@Query("SELECT u from Users u WHERE u.username=?1")
	List<Users> FetchTheUserDetailsByUsername(String username);
}
