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
public class UserDAOImpl implements UserDAO {
	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean authenticate(String userName, String password) {
	
		String sqlQuery = CrestaQueryConstants.QRY_GET_CREDENTIAL_DETAILS;
		List<LoginBean> loginList = jdbcTemplate.query(sqlQuery, new Object[] { userName, password },
				new RowMapper<LoginBean>() {

					@Override
					public LoginBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						LoginBean loginBean = new LoginBean();
						loginBean.setUserName(rs.getString("user_name"));
						loginBean.setPassword(rs.getString("password"));
						return loginBean;
					}
				});
		if (!loginList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public UserBean getUserDetails(String userName, String password) {

		String sqlQuery = CrestaQueryConstants.QRY_GET_CREDENTIAL_DETAILS;
		List<UserBean> userList = jdbcTemplate.query(sqlQuery, new Object[] { userName, password },
				new RowMapper<UserBean>() {

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
		UserBean userBean = new UserBean();
		userBean = userList.get(0);
		return userBean;
	}
}
