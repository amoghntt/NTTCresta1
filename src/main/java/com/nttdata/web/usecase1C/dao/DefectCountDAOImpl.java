package com.nttdata.web.usecase1C.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class DefectCountDAOImpl implements DefectCountDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DefectCountModel> getDefectCountAndReleaseData(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_COUNT_DATA;
		List<DefectCountModel> defectDensityModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { Integer.parseInt(userId) }, new RowMapper<DefectCountModel>() {
					@Override
					public DefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectCountModel defectDensityModel = new DefectCountModel();

						defectDensityModel.setDefectDensity((rs.getInt("defect_count")));
						defectDensityModel.setRelease(rs.getInt("release_version"));

						return defectDensityModel;
					}
				});
		return defectDensityModelList;

	}

	@Override
	public List<DefectCountModel> getDefectCountData(int userid) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_COUNT_USECASE1C_TELEPHONICA;

		List<DefectCountModel> defectCountModelList = jdbcTemplate.query(sqlQuery, new Object[] { userid },
				new RowMapper<DefectCountModel>() {
					@Override
					public DefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectCountModel defectCountModel = new DefectCountModel();
						defectCountModel.setkLoc(rs.getInt("KLOC"));
						defectCountModel.setDefectDensity(rs.getInt("defect_count"));
						defectCountModel.setApplicationComplexity(rs.getInt("application_complexity"));
						defectCountModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectCountModel.setTechnicalSkills(rs.getInt("technical_skills"));
						defectCountModel.setCodeReviewComments(rs.getInt("code_review_comments"));
						defectCountModel.setDesignReviewComments(rs.getInt("design_review_comments"));
						defectCountModel.setTestCaseCount(rs.getInt("test_case_count"));
						defectCountModel.setRequirementsQueryCount(rs.getInt("requirements_query_count"));
						defectCountModel.setRelease(rs.getInt("rel_id"));

						return defectCountModel;
					}
				});
		return defectCountModelList;
	}

}
