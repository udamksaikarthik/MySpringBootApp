package com.spring.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Expenses")
public class Expenses {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expense_id;
	private String expense_type;
	private String expense_name;
	private Double expense_amount;
	private String username;
	private String expense_added_date;
	
	public Expenses() {
		
	}
	
	

	public Expenses(Long expense_id, String expense_type, String expense_name, Double expense_amount, String username,
			String expense_added_date) {
		super();
		this.expense_id = expense_id;
		this.expense_type = expense_type;
		this.expense_name = expense_name;
		this.expense_amount = expense_amount;
		this.username = username;
		this.expense_added_date = expense_added_date;
	}



	public Long getExpense_id() {
		return expense_id;
	}

	public void setExpense_id(Long expense_id) {
		this.expense_id = expense_id;
	}

	public String getExpense_type() {
		return expense_type;
	}

	public void setExpense_type(String expense_type) {
		this.expense_type = expense_type;
	}

	public String getExpense_name() {
		return expense_name;
	}

	public void setExpense_name(String expense_name) {
		this.expense_name = expense_name;
	}

	public Double getExpense_amount() {
		return expense_amount;
	}

	public void setExpense_amount(Double expense_amount) {
		this.expense_amount = expense_amount;
	}

	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getExpense_added_date() {
		return expense_added_date;
	}

	public void setExpense_added_date(String expense_added_date) {
		this.expense_added_date = expense_added_date;
	}



	@Override
	public String toString() {
		return "Expenses [expense_id=" + expense_id + ", expense_type=" + expense_type + ", expense_name="
				+ expense_name + ", expense_amount=" + expense_amount + ", username=" + username
				+ ", expense_added_date=" + expense_added_date + "]";
	}

	
	
}
