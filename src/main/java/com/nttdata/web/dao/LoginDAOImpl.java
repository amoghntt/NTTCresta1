package com.nttdata.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.web.model.LoginBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
@Transactional(readOnly = true)
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean authenticateUser(LoginBean loginBean) {
		boolean userExists = false;
		String sqlQuery = CrestaQueryConstants.QRY_GET_CREDENTIAL_DETAILS;
		List<LoginBean> loginList = jdbcTemplate.query(sqlQuery,
				new Object[] { loginBean.getUserName(), loginBean.getPassword() }, new RowMapper<LoginBean>() {

					@Override
					public LoginBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						LoginBean loginBean = new LoginBean();
						loginBean.setUserName(rs.getString("user_name"));
						loginBean.setPassword(rs.getString("password"));
						return loginBean;
					}
				});
		if (!loginList.isEmpty()) {
			userExists = true;
		}
		return userExists;
	}

	@Override
	public UserBean getUserDetails(LoginBean logInBean) {

		String sqlQuery = CrestaQueryConstants.QRY_GET_CREDENTIAL_DETAILS;
		List<UserBean> userList = jdbcTemplate.query(sqlQuery,
				new Object[] { logInBean.getUserName(), logInBean.getPassword() }, new RowMapper<UserBean>() {

					@Override
					public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserBean userBean = new UserBean();
						userBean.setUserName(rs.getString("user_name"));
						userBean.setPassword(rs.getString("password"));
						userBean.setUserId(rs.getString("id"));
						userBean.setEmailId(rs.getString("email"));
						userBean.setFirstName(rs.getString("first_name"));
						userBean.setLastName(rs.getString("last_name"));
						return userBean;
					}
				});

		return userList.get(0);
	}
}
