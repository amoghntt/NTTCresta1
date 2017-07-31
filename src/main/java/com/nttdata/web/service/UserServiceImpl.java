package com.nttdata.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.web.dao.UserDAO;
import com.nttdata.web.model.UserBean;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean authenticate(String userName, String password) {
		boolean result = userDAO.authenticate(userName, password);
		return result;
	}

	@Override
	public UserBean getUserDetails(String userName, String password) {
		return userDAO.getUserDetails(userName, password);
	}

}
