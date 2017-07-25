package com.nttdata.web.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserBean {

	private String userName;
	private String userId;
	private String password;
	private List<ProjectBean> projectBeanList = new ArrayList<ProjectBean>();
	private String emailId;
	private String firstName;
	private String lastName;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public List<ProjectBean> getProjectBeanList() {
		return projectBeanList;
	}

	public void setProjectBeanList(List<ProjectBean> projectBeanList) {
		this.projectBeanList = projectBeanList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
