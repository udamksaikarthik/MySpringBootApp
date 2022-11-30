package com.spring.demo.service.fulldayplanner;

import java.util.HashMap;
import java.util.List;

import com.spring.demo.entity.Activity;

public interface FullDayPlannerServiceImpl {

	HashMap<String, String> addActivity(String userName, String startTime, String endTime, String activityType, String activityInfo);

	List<Activity> getActivities(String username);

	void delete(Integer id);

}
