package com.nttdata.web.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.web.dao.UserDAO;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.utils.CrestaQueryConstants;

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

	@SuppressWarnings("unused")
	@Override
	public void setUserProperties() {
			try {
			InputStream input = new FileInputStream(CrestaQueryConstants.PROPERTIES_FILE_PATH);
			if (input == null) {
				System.out.println("Sorry, unable to find properties file");

			}
			Properties externalProperties = new Properties();
			externalProperties.load(input);
			String resourceName = "messages.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties props = new Properties();
			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			    props.load(resourceStream);
			
			props.setProperty("mysql_cresta_url", externalProperties.getProperty("mysql_cresta_url"));
			props.setProperty("mysql_cresta_user", externalProperties.getProperty("mysql_cresta_user"));
			props.setProperty("mysql_cresta_password", externalProperties.getProperty("mysql_cresta_password"));
			props.store(new FileOutputStream("messages.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}catch (IOException ex) {
		ex.printStackTrace();
	}
	}
}
