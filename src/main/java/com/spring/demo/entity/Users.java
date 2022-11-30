package com.spring.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Username is Mandatory!")
	private String username;
	@NotBlank(message = "Password is Mandatory!")
	private String password;
	@NotBlank(message = "Password is Mandatory!")
	@Transient
	private String confirmpassword;
	@NotBlank(message = "Email is Mandatory!")
	@Email(message = "Email is Not Valid!")
	private String email;
	@Size(min = 10, max = 10, message = "Number Should be 10-Digit!")
	private String contactnumber;
	@NotBlank(message = "Date Of Birth is Mandatory!")
	private String dateofbirth;
	private String address;
	private int age;
	@Column(columnDefinition = "varchar(255) default 'ROLE_USER'")
	private String roles;
	@Column(columnDefinition = "boolean default true")
	private boolean active;


	public Users() {

	}


	public Users(Long id, @NotEmpty(message = "Username is Mandatory!") String username,
			@NotBlank(message = "Password is Mandatory!") String password,
			@NotBlank(message = "Password is Mandatory!") String confirmpassword,
			@NotBlank(message = "Email is Mandatory!") @Email(message = "Email is Not Valid!") String email,
			@Size(min = 10, max = 10, message = "Number Should be 10-Digit!") String contactnumber,
			@NotBlank(message = "Date Of Birth is Mandatory!") String dateofbirth, String address, int age,
			String roles, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.email = email;
		this.contactnumber = contactnumber;
		this.dateofbirth = dateofbirth;
		this.address = address;
		this.age = age;
		this.roles = roles;
		this.active = active;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	public String getRoles() {
		return roles;
	}







	public void setRoles(String roles) {
		this.roles = roles;
	}







	public boolean isActive() {
		return active;
	}







	public void setActive(boolean active) {
		this.active = active;
	}







	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", confirmpassword="
				+ confirmpassword + ", email=" + email + ", contactnumber=" + contactnumber + ", dateofbirth="
				+ dateofbirth + ", address=" + address + ", age=" + age + ", roles=" + roles + ", active=" + active
				+ "]";
	}














}
