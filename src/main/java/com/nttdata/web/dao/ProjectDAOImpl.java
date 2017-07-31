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

import com.nttdata.web.model.ProjectBean;
import com.nttdata.web.utils.CrestaQueryConstants;


@Repository
@Transactional(readOnly = true)
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ProjectBean> getProjectListForUser(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_PROJECT_LIST;
		List<ProjectBean> projectList = jdbcTemplate.query(sqlQuery, new Object[] { userId },
				new RowMapper<ProjectBean>() {

					@Override
					public ProjectBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProjectBean projectBean = new ProjectBean();
						projectBean.setRedmineProjectId(rs.getInt("redmine_proj_id"));
						projectBean.setProjectName(rs.getString("proj_name"));
						projectBean.setProjectDescription(rs.getString("proj_description"));

						return projectBean;
					}
				});

		return projectList;
	}

}
