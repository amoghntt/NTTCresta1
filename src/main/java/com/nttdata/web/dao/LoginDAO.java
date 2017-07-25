package com.nttdata.web.dao;

import com.nttdata.web.model.LoginBean;
import com.nttdata.web.model.UserBean;

public interface LoginDAO {
	public boolean authenticateUser(LoginBean loginBean);
	public UserBean getUserDetails(LoginBean logInBean);
}
