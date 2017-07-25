package com.nttdata.web.usecase1D.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class FunctionalDefectCountDAOImpl implements FunctionalDefectCountDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FunctionalDefectCountModel> getFunctionalDefectCountAndReleaseData(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_FUNCTIONAL_DEFECT_COUNT;
		List<FunctionalDefectCountModel> functionalDefectCountModelList = jdbcTemplate.query(sqlQuery, new Object[] {Integer.parseInt(userId)}, new RowMapper<FunctionalDefectCountModel>() {
			@Override
			public FunctionalDefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				FunctionalDefectCountModel functionalDefectCountModel = new FunctionalDefectCountModel();
				
				functionalDefectCountModel.setRelease((rs.getInt("release_version")));
				functionalDefectCountModel.setDefectCount((rs.getInt("defect_count")));
				functionalDefectCountModel.setKiloLinesOfCode(rs.getInt("KLOC"));
				functionalDefectCountModel.setTestCaseCount(rs.getInt("test_case_count"));
				functionalDefectCountModel.setApplicationComplexity(rs.getInt("application_complexity"));
				functionalDefectCountModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
				functionalDefectCountModel.setTechnicalSkills(rs.getInt("technical_skills"));
				functionalDefectCountModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
				functionalDefectCountModel.setCodeReviewComments((rs.getInt("code_review_comments")));
				functionalDefectCountModel.setDesignReviewComments((rs.getInt("design_review_comments")));
				return functionalDefectCountModel;
			}
		});
		return functionalDefectCountModelList;
		
	}
	
	@Override
	public List<FunctionalDefectCountModel> getFunctionalDefectCountAndReleaseDataTelephonica(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_FUNCTIONAL_DEFECT_COUNT_DATA_FOR_USECASE1D_TELEPHONICA;
		List<FunctionalDefectCountModel> functionalDefectCountModelList = jdbcTemplate.query(sqlQuery, new Object[] {Integer.parseInt(userId)}, new RowMapper<FunctionalDefectCountModel>() {
			@Override
			public FunctionalDefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				FunctionalDefectCountModel functionalDefectCountModel = new FunctionalDefectCountModel();
				
				functionalDefectCountModel.setRelease((rs.getInt("rel_id")));
				functionalDefectCountModel.setDefectCount((rs.getInt("defect_count")));
				functionalDefectCountModel.setKiloLinesOfCode(rs.getInt("KLOC"));
				functionalDefectCountModel.setTestCaseCount(rs.getInt("test_case_count"));
				functionalDefectCountModel.setApplicationComplexity(rs.getInt("application_complexity"));
				functionalDefectCountModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
				functionalDefectCountModel.setTechnicalSkills(rs.getInt("technical_skills"));
				functionalDefectCountModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
				functionalDefectCountModel.setCodeReviewComments((rs.getInt("code_review_comments")));
				functionalDefectCountModel.setDesignReviewComments((rs.getInt("design_review_comments")));
				return functionalDefectCountModel;
			}
		});
		return functionalDefectCountModelList;
		
	}
	
	@Override
	public List<ReleaseDetails> getLastTenPredictions(int algorithmId) {
		// TODO Auto-generated method stub
		String sqlQuery = CrestaQueryConstants.QRY_GET_LAST_TEN_PREDICTIONS_FUNCTIONAL_DEFECT_COUNT;

		List<ReleaseDetails> lastTenPredictions = jdbcTemplate.query(sqlQuery, new Object[] {},
				new RowMapper<ReleaseDetails>() {
					@Override
					public ReleaseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReleaseDetails lastPrediction = new ReleaseDetails();
						lastPrediction.setReleaseid(rs.getInt("releaseid"));
						switch (algorithmId) {
						case 0:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1DJubatus"));
							break;
						case 2:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1DLinearRegression"));
							break;
						case 3:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1DSVRLinear"));
							break;
						case 4:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1DSVRRBF"));
							break;
						default:
							break;
						}

						return lastPrediction;
					}
				});
		return lastTenPredictions;
	}
	
	@Override
	public List<FunctionalDefectCountModel> getAllData(String userId) {
		
		String sqlQuery = CrestaQueryConstants.QRY_GET_ALL_DATA_FOR_USECASE1D_TELEPHONICA;

		List<FunctionalDefectCountModel> functionalDefectCountModelList = jdbcTemplate.query(sqlQuery, new Object[] {Integer.parseInt(userId)}, new RowMapper<FunctionalDefectCountModel>() {
			@Override
			public FunctionalDefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				FunctionalDefectCountModel functionalDefectCountModel = new FunctionalDefectCountModel();
				
				functionalDefectCountModel.setRelease((rs.getInt("rel_id")));
				functionalDefectCountModel.setDefectCount((rs.getInt("defect_count")));
				functionalDefectCountModel.setKiloLinesOfCode(rs.getInt("KLOC"));
				functionalDefectCountModel.setTestCaseCount(rs.getInt("test_case_count"));
				functionalDefectCountModel.setApplicationComplexity(rs.getInt("application_complexity"));
				functionalDefectCountModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
				functionalDefectCountModel.setTechnicalSkills(rs.getInt("technical_skills"));
				functionalDefectCountModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
				functionalDefectCountModel.setCodeReviewComments((rs.getInt("code_review_comments")));
				functionalDefectCountModel.setDesignReviewComments((rs.getInt("design_review_comments")));
				functionalDefectCountModel.setTprName(rs.getString("tpr_name"));
				return functionalDefectCountModel;
			}
		});
		return functionalDefectCountModelList;
	}
}