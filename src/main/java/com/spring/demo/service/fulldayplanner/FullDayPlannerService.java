package com.spring.demo.service.fulldayplanner;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.fulldayplanner.FullDayPlannerDao;
import com.spring.demo.entity.Activity;

@Service
public class FullDayPlannerService implements FullDayPlannerServiceImpl{
	
	@Autowired
	private FullDayPlannerDao fullDayPlannerDao;

	@Override
	public HashMap<String, String> addActivity(String userName, String startTime, String endTime, String activityType,
			String activityInfo) {
		HashMap<String,String> errorAndSuccessDetails = new HashMap<>();
		errorAndSuccessDetails.put("success_message","");
		errorAndSuccessDetails.put("conflict_error_message","");
		System.out.println("startTime: "+startTime);
		System.out.println("endTime: "+endTime);
		String startTimeHours = startTime.substring(0, 2);
		String startTimeMins = startTime.substring(3, 5);
		String endTimeHours = endTime.substring(0, 2);
		String endTimeMins = endTime.substring(3, 5);
		System.out.println("startTimeHours: "+startTimeHours);
		System.out.println("startTimeMins: "+startTimeMins);
		System.out.println("endTimeHours: "+endTimeHours);
		System.out.println("endTimeMins: "+endTimeMins);
		System.out.println("userName: "+userName);
		System.out.println("activityType: "+activityType);
		System.out.println("activityInfo: "+activityInfo);
		Activity activity = new Activity(userName, startTimeHours, startTimeMins, endTimeHours, endTimeMins, activityType.toUpperCase(), activityInfo);
		System.out.println(activity.toString());
		boolean flag = true;
		List<Activity> existingActivities = fullDayPlannerDao.fetchActivities(userName);
		System.out.println("existingActivities from Service: "+existingActivities);
		int existingActivitiesSize = (existingActivities!=null)? existingActivities.size():0;
		
		System.out.println("existingActivitiesSize: "+existingActivitiesSize);
		
		for(int i=0;i<existingActivitiesSize;i++) {
			System.out.println(existingActivities.get(i).toString());
			Integer existingActivitiesStartTimeHours = Integer.parseInt(existingActivities.get(i).getStart_time_hours());
			Integer existingActivitiesStartTimeMins = Integer.parseInt(existingActivities.get(i).getStart_time_mins());
			Integer existingActivitiesEndTimeHours = Integer.parseInt(existingActivities.get(i).getEnd_time_hours());
			Integer existingActivitiesEndTimeMins = Integer.parseInt(existingActivities.get(i).getEnd_time_mins());
			for(int j=existingActivitiesStartTimeHours; j<existingActivitiesEndTimeHours; j++) {
				if(Integer.parseInt(startTimeHours)==j ||  Integer.parseInt(endTimeMins)==j) {
					System.out.println("Error");
					errorAndSuccessDetails.put("conflict_error_message","There is conflict with existing Activity!");
					flag = false;
					break;
				}
				if(j==24) {
					j=0;
				}
			}
		}
		
		if(Integer.parseInt(startTimeHours)>Integer.parseInt(endTimeHours)) {
			errorAndSuccessDetails.put("conflict_error_message","StartTime can't be greater than EndTime and both can't be same!");
			flag = false;
		}
		
		if(Integer.parseInt(startTimeHours)==Integer.parseInt(endTimeHours)) {
			if(Integer.parseInt(startTimeMins)>=Integer.parseInt(endTimeMins)) {
				errorAndSuccessDetails.put("conflict_error_message","StartTime can't be greater than EndTime and both can't be same!");
				flag = false;
			}
		}
		
		if(flag) {
			fullDayPlannerDao.addActivity(activity);
			errorAndSuccessDetails.put("success_message","The Activity has been added successfully!");
		}
		
		return errorAndSuccessDetails;
	}

	@Override
	public List<Activity> getActivities(String username) {
		return fullDayPlannerDao.getActivities(username);
	}

	@Override
	public void delete(Integer id) {
		fullDayPlannerDao.delete(id);
		
	}

}
