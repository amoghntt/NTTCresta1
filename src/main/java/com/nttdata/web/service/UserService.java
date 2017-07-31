package com.nttdata.web.service;

import com.nttdata.web.model.UserBean;

public interface UserService {
	boolean authenticate(String userName, String password);
	UserBean getUserDetails(String userName,String password);
		
}
