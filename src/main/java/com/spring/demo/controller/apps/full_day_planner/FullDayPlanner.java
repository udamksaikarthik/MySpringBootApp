package com.spring.demo.controller.apps.full_day_planner;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.entity.Activity;
import com.spring.demo.security.MyUserDetails;
import com.spring.demo.service.fulldayplanner.FullDayPlannerServiceImpl;

@Controller
public class FullDayPlanner {
	
	@Autowired
	private FullDayPlannerServiceImpl fullDayPlannerServiceImpl;
	
	@GetMapping("/apps/24_hour_planner")
	public ModelAndView getFullDayPlanner(@AuthenticationPrincipal MyUserDetails user){
		ModelAndView mv = new ModelAndView();
		List<Activity> activitiesList = fullDayPlannerServiceImpl.getActivities(user.getUsername());
		double workPercentage = 0;
		double sleepPercentage = 0;
		double exercisePercentage = 0;
		double partyPercentage = 0;
		double otherPercentage = 0;
		if(!activitiesList.isEmpty() || activitiesList!=null) {
			double totalHours = 24;
			double workTotalHours = 0;
			double sleepTotalHours = 0;
			double exerciseTotalHours = 0;
			double partyTotalHours = 0;
			double otherTotalHours = 0;
			
			for(Activity activity: activitiesList) {
				
				if(activity.getActivity_type().equalsIgnoreCase("work")) {
					workTotalHours = workTotalHours + (Integer.parseInt(activity.getEnd_time_hours())  - Integer.parseInt(activity.getStart_time_hours()));
				}
				else if(activity.getActivity_type().equalsIgnoreCase("exercise")) {
					exerciseTotalHours = exerciseTotalHours + (Integer.parseInt(activity.getEnd_time_hours())  - Integer.parseInt(activity.getStart_time_hours()));
				}
				else if(activity.getActivity_type().equalsIgnoreCase("sleep")) {
					sleepTotalHours = sleepTotalHours + (Integer.parseInt(activity.getEnd_time_hours())  - Integer.parseInt(activity.getStart_time_hours()));
					System.out.println("sleepTotalHours: "+sleepTotalHours);
				}
				else if(activity.getActivity_type().equalsIgnoreCase("party")) {
					partyTotalHours = partyTotalHours + (Integer.parseInt(activity.getEnd_time_hours())  - Integer.parseInt(activity.getStart_time_hours()));
				}
				else if(activity.getActivity_type().equalsIgnoreCase("other")) {
					otherTotalHours = otherTotalHours + (Integer.parseInt(activity.getEnd_time_hours())  - Integer.parseInt(activity.getStart_time_hours()));
				}
			}
			System.out.println("sleepTotalHours Outside: "+sleepTotalHours);
			System.out.println("totalHours Outside: "+totalHours);
			workPercentage = Math.round((workTotalHours /totalHours)*100);
			sleepPercentage = Math.round((sleepTotalHours /totalHours)*100);
			exercisePercentage = Math.round((exerciseTotalHours /totalHours)*100);
			partyPercentage = Math.round((partyTotalHours /totalHours)*100);
			otherPercentage = Math.round((otherTotalHours /totalHours)*100);
			System.out.println("workPercentage: "+workPercentage);
			System.out.println("sleepPercentage: "+sleepPercentage);
		}
		mv.addObject("workPercentage", workPercentage);
		mv.addObject("sleepPercentage", sleepPercentage);
		mv.addObject("exercisePercentage", exercisePercentage);
		mv.addObject("partyPercentage", partyPercentage);
		mv.addObject("otherPercentage", otherPercentage);
		mv.addObject("success_flag", false);
		mv.addObject("conflict_error_message_flag", false);
		mv.setViewName("FullDayPlanner.html");
		return mv;
	}
	
	@PostMapping("/apps/24_hour_planner/addActivity")
	public ModelAndView addActivity(@AuthenticationPrincipal MyUserDetails user,@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("activityType") String activityType,
			@RequestParam("activityInfo") String activityInfo) {
		HashMap<String,String> errorAndSuccessDetails= fullDayPlannerServiceImpl.addActivity(user.getUsername(), startTime, endTime, activityType, activityInfo);
		ModelAndView mv = new ModelAndView();
		
		String success_message_msg = (errorAndSuccessDetails.get("success_message")!=null)?
				errorAndSuccessDetails.get("success_message"):"";
		String error_message_msg = (errorAndSuccessDetails.get("conflict_error_message")!=null)?
				errorAndSuccessDetails.get("conflict_error_message"):"";
		if(success_message_msg.equals("") || success_message_msg.isEmpty()) {
			mv.addObject("success_flag", false);
		}else {
			mv.addObject("success_flag", true);
		}
		if(error_message_msg.equals("") || error_message_msg.isEmpty()) {
			mv.addObject("conflict_error_message_flag", false);
		}else {
			mv.addObject("conflict_error_message_flag", true);
		}
		System.out.println("success_message: "+errorAndSuccessDetails.get("success_message"));
		System.out.println("conflict_error_message_flag: "+error_message_msg);
		mv.addObject("success_message", errorAndSuccessDetails.get("success_message"));
		mv.addObject("conflict_error_message", errorAndSuccessDetails.get("conflict_error_message"));
		mv.addObject("addActivity_translateY", true);
		mv.setViewName("FullDayPlanner.html");
		return mv;
	}
	
	@GetMapping("/apps/24_hour_planner/allActivities")
	public ModelAndView getAllActivities(@AuthenticationPrincipal MyUserDetails user) {
		ModelAndView mv = new ModelAndView();
		List<Activity> activitiesList = fullDayPlannerServiceImpl.getActivities(user.getUsername());
		for(Activity a:activitiesList) {
			System.out.println(a.toString());
		}
		mv.addObject("activitiesList", activitiesList);
		mv.setViewName("AllActivities.html");
		return mv;
	}
	
	@GetMapping("/apps/24_hour_planner/allActivities/delete/{id}")
	public ModelAndView getAllActivities(@PathVariable("id") Integer id) {
		fullDayPlannerServiceImpl.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/apps/24_hour_planner/allActivities");
		return mv;
	}
}
