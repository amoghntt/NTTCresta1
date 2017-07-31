package com.nttdata.web.usecase1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1.model.DefectDensityModel;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class DefectDensityDAOImpl implements DefectDensityDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DefectDensityModel> getDefectDensityAndReleaseData(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1;

		List<DefectDensityModel> defectDensityModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { Integer.parseInt(userId) }, new RowMapper<DefectDensityModel>() {
					@Override
					public DefectDensityModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDensityModel defectDensityModel = new DefectDensityModel();

						defectDensityModel.setRelease((rs.getInt("release_version")));
						defectDensityModel.setDefectDensity((Math.round(rs.getInt("defect_count")/rs.getInt("KLOC"))));
						defectDensityModel.setKiloLinesOfCode(rs.getInt("KLOC"));
						defectDensityModel.setTestCaseCount(rs.getInt("test_case_count"));
						defectDensityModel.setApplicationComplexity(rs.getInt("application_complexity"));
						defectDensityModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectDensityModel.setTechnicalSkills(rs.getInt("technical_skills"));
						defectDensityModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
						defectDensityModel.setCodeReviewComments((rs.getInt("code_review_comments")));
						defectDensityModel.setDesignReviewComments((rs.getInt("design_review_comments")));
						return defectDensityModel;
					}
				});
		return defectDensityModelList;

	}

	@Override
	public List<DefectDensityModelTelephonica> getDefectDensityAndReleaseDataTelephonica(String userId) {
		// TODO Auto-generated method stub
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1_TELEPHONICA;

		List<DefectDensityModelTelephonica> defectDensityModelListTelephonica = jdbcTemplate.query(sqlQuery,
				new Object[] { Integer.parseInt(userId) }, new RowMapper<DefectDensityModelTelephonica>() {
					@Override
					public DefectDensityModelTelephonica mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDensityModelTelephonica defectDensityModelTelephonica = new DefectDensityModelTelephonica();

						defectDensityModelTelephonica.setRelease((rs.getInt("rel_id")));
						defectDensityModelTelephonica.setDefectDensity((Math.round(rs.getInt("defect_count")/rs.getInt("KLOC"))));
						defectDensityModelTelephonica.setKiloLinesOfCode(rs.getInt("KLOC"));
						defectDensityModelTelephonica.setTestCaseCount(rs.getInt("test_case_count"));
						defectDensityModelTelephonica.setApplicationComplexity(rs.getInt("application_complexity"));
						defectDensityModelTelephonica.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectDensityModelTelephonica.setTechnicalSkills(rs.getInt("technical_skills"));
						defectDensityModelTelephonica.setRequirementQueryCount(rs.getInt("requirements_query_count"));
						defectDensityModelTelephonica.setCodeReviewComments((rs.getInt("code_review_comments")));
						defectDensityModelTelephonica.setDesignReviewComments((rs.getInt("design_review_comments")));
						return defectDensityModelTelephonica;
					}
				});
		return defectDensityModelListTelephonica;
	}
	
	@Override
	public List<ReleaseDetails> getLastTenPredictions(int algorithmId) {
		// TODO Auto-generated method stub
		String sqlQuery = CrestaQueryConstants.QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_DENSITY;

		List<ReleaseDetails> lastTenPredictions = jdbcTemplate.query(sqlQuery,
				new Object[] {  }, new RowMapper<ReleaseDetails>() {
					@Override
					public ReleaseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReleaseDetails lastPrediction = new ReleaseDetails();
						lastPrediction.setReleaseid(rs.getInt("releaseid"));
						switch (algorithmId) {
						case 0:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1Jubatus"));
							break;
						case 2:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1LinearRegression"));
							break;
						case 3:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1SVRLinear"));
							break;
						case 4:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1SVRRBF"));
							break;
						default:
							break;
						}
						
						return lastPrediction;
					}
				});
		return lastTenPredictions;
	}
	
	
}
