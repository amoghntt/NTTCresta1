package com.nttdata.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.model.TelephonicaBean;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class AceMasterDaoImpl implements AceMasterDao{
	
	@Autowired
	@Resource(name = "jdbcTelephonicaTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TelephonicaBean> getPieChartData() {
		String sqlQuery = CrestaQueryConstants.QRY_GET_PIECHART_DATA;
		List<TelephonicaBean> resultList = jdbcTemplate.query(sqlQuery, new Object[] { },
				new RowMapper<TelephonicaBean>() {

					@Override
					public TelephonicaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						TelephonicaBean telephonicaDataBean = new TelephonicaBean();
						telephonicaDataBean.setCountOfBugs(rs.getInt("No_of_Bugs"));
						telephonicaDataBean.setCategory(rs.getString("bg_user_02"));
										
						return telephonicaDataBean;
					}
				});
		return resultList;
	}
	
	@Override
	public List<TelephonicaBean> getTestEnvironmentORSoftwareData(String category) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_PROJECT_WISE_TEST_ENVIRONMENT_DEFECTS;
		List<TelephonicaBean> resultList = jdbcTemplate.query(sqlQuery, new Object[] { category},
				new RowMapper<TelephonicaBean>() {

					@Override
					public TelephonicaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						TelephonicaBean telephonicaDataBean = new TelephonicaBean();
						telephonicaDataBean.setCountOfBugs(rs.getInt("No_of_Bugs"));
						telephonicaDataBean.setProjectName(rs.getString("bg_project"));
										
						return telephonicaDataBean;
					}
				});
		return resultList;
	}
}
