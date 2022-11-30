package com.spring.demo.dao.fulldayplanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.demo.entity.Activity;

@Repository
public class FullDayPlannerDao {
	@Autowired
	private FullDayPlannerDaoImpl fullDayPlannerDaoImpl;

	public void addActivity(Activity activity) {
		fullDayPlannerDaoImpl.save(activity);
	}

	public List<Activity> fetchActivities(String userName) {
		List<Activity> existingActivities = new ArrayList<>();
		existingActivities = fullDayPlannerDaoImpl.findAllByUserName(userName);
		System.out.println("existingActivities from DAO: "+existingActivities);
		return existingActivities;
	}

	public List<Activity> getActivities(String username) {
		List<Activity> activitiesList = new ArrayList<>();
		activitiesList = fullDayPlannerDaoImpl.fetchAllActivitiesUser(username);
		return activitiesList;
	}

	public void delete(Integer id) {
		fullDayPlannerDaoImpl.deleteById(id);
	}
	
}
