package com.spring.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Activity")
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer activity_id;
	private String user_name;
	private String start_time_hours;
	private String start_time_mins;
	private String end_time_hours;
	private String end_time_mins;
	private String activity_type;
	private String activity_info;
	
	public Activity() {
		
	}
	
	public Activity(String user_name, String start_time_hours, String start_time_mins,
			String end_time_hours, String end_time_mins, String activity_type, String activity_info) {
		super();
		this.user_name = user_name;
		this.start_time_hours = start_time_hours;
		this.start_time_mins = start_time_mins;
		this.end_time_hours = end_time_hours;
		this.end_time_mins = end_time_mins;
		this.activity_type = activity_type;
		this.activity_info = activity_info;
	}
	
	public Activity(Integer activity_id, String user_name, String start_time_hours, String start_time_mins,
			String end_time_hours, String end_time_mins, String activity_type, String activity_info) {
		super();
		this.activity_id = activity_id;
		this.user_name = user_name;
		this.start_time_hours = start_time_hours;
		this.start_time_mins = start_time_mins;
		this.end_time_hours = end_time_hours;
		this.end_time_mins = end_time_mins;
		this.activity_type = activity_type;
		this.activity_info = activity_info;
	}
	
	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStart_time_hours() {
		return start_time_hours;
	}

	public void setStart_time_hours(String start_time_hours) {
		this.start_time_hours = start_time_hours;
	}

	public String getStart_time_mins() {
		return start_time_mins;
	}

	public void setStart_time_mins(String start_time_mins) {
		this.start_time_mins = start_time_mins;
	}

	public String getEnd_time_hours() {
		return end_time_hours;
	}

	public void setEnd_time_hours(String end_time_hours) {
		this.end_time_hours = end_time_hours;
	}

	public String getEnd_time_mins() {
		return end_time_mins;
	}

	public void setEnd_time_mins(String end_time_mins) {
		this.end_time_mins = end_time_mins;
	}

	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	public String getActivity_info() {
		return activity_info;
	}

	public void setActivity_info(String activity_info) {
		this.activity_info = activity_info;
	}

	@Override
	public String toString() {
		return "Activity [activity_id=" + activity_id + ", user_name=" + user_name + ", start_time_hours="
				+ start_time_hours + ", start_time_mins=" + start_time_mins + ", end_time_hours=" + end_time_hours
				+ ", end_time_mins=" + end_time_mins + ", activity_type=" + activity_type + ", activity_info="
				+ activity_info + "]";
	}
	
	
}
