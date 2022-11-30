package com.spring.demo.dao.fulldayplanner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.demo.entity.Activity;

public interface FullDayPlannerDaoImpl extends JpaRepository<Activity, Integer>{
	
	@Query("SELECT a FROM Activity a WHERE a.user_name = ?1")
	List<Activity> findAllByUserName(String userName);

	@Query("SELECT a FROM Activity a WHERE a.user_name = ?1 ORDER BY a.start_time_hours")
	List<Activity> fetchAllActivitiesUser(String username);

}
