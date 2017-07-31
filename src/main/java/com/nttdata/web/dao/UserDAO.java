package com.nttdata.web.dao;

import com.nttdata.web.model.UserBean;

public interface UserDAO {
	public boolean authenticate(String userName, String password);
	public UserBean getUserDetails(String userName, String password);
}
