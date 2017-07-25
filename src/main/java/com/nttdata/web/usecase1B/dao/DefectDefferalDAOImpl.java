package com.nttdata.web.usecase1B.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class DefectDefferalDAOImpl implements DefectDefferalDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DefectDeferralModel> getDefectDefferalAndReleaseData(String userid, String pred_code) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DEFERRAL_DATA;

		List<DefectDeferralModel> defectDeferralModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { userid, pred_code }, new RowMapper<DefectDeferralModel>() {
					@Override
					public DefectDeferralModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDeferralModel defectDeferralModel = new DefectDeferralModel();
						defectDeferralModel.setDefectDeferralCount(rs.getInt("defect_deferral_rate"));
						defectDeferralModel.setDefectSeverity(rs.getInt("defect_severity"));
						defectDeferralModel.setDefectPriority(rs.getInt("defect_priority"));
						defectDeferralModel.setEffortToFixDefect(rs.getInt("effort_to_fix_defect"));
						defectDeferralModel.setCostToFixDefect(rs.getInt("cost_to_fix_defect"));
						defectDeferralModel.setImpactOfDefectFix(rs.getInt("impact_of_defect_fix"));
						defectDeferralModel
								.setFeasibilityWithinMilestone(rs.getBoolean("feasibility_within_milestone"));
						defectDeferralModel.setAvailabilityOfBudget(rs.getInt("availability_of_budget"));
						defectDeferralModel.setComplexityofDefectFix(rs.getInt("complexity_of_defect_fix"));
						defectDeferralModel.setUserId(rs.getInt("userid"));
						defectDeferralModel.setReleaseVersion(rs.getInt("release_version"));
						return defectDeferralModel;
					}
				});
		return defectDeferralModelList;
	}

	@Override
	public List<DefectDeferralModelTelephonica> getDefectDefferalAndReleaseTelephonicaData(String userid,
			String pred_code) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1B_TELEPHONICA;

		List<DefectDeferralModelTelephonica> defectDeferralModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { userid }, new RowMapper<DefectDeferralModelTelephonica>() {
					@Override
					public DefectDeferralModelTelephonica mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDeferralModelTelephonica defectDeferralModel = new DefectDeferralModelTelephonica();
						defectDeferralModel.setDefectDeferralCount(rs.getInt("defect_deferral_rate"));
						defectDeferralModel.setBg_Severity(rs.getInt("bg_severity"));
						defectDeferralModel.setBg_Priority(rs.getInt("bg_priority"));
						defectDeferralModel.setEffortToFixDefect(rs.getInt("effort_to_fix_defect"));
						defectDeferralModel.setCostToFixDefect(rs.getInt("cost_to_fix_defect"));
						defectDeferralModel.setImpactOfDefectFix(rs.getInt("impact_of_defect_fix"));
						defectDeferralModel
								.setFeasibilityWithinMilestone(rs.getBoolean("feasibility_within_milestone"));
						defectDeferralModel.setAvailabilityOfBudget(rs.getInt("availability_of_budget"));
						defectDeferralModel.setComplexityofDefectFix(rs.getInt("complexity_of_defect_fix"));
						defectDeferralModel.setUserId(rs.getInt("user_id"));
						defectDeferralModel.setRel_Id(rs.getInt("rel_id"));
						return defectDeferralModel;
					}
				});
		return defectDeferralModelList;
	}

	@Override
	public List<ReleaseDetails> getLastTenPredictions(int algorithmId) {
		// TODO Auto-generated method stub
		String sqlQuery = CrestaQueryConstants.QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_DEFERRAL;

		List<ReleaseDetails> lastTenPredictions = jdbcTemplate.query(sqlQuery, new Object[] {},
				new RowMapper<ReleaseDetails>() {
					@Override
					public ReleaseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReleaseDetails lastPrediction = new ReleaseDetails();
						lastPrediction.setReleaseid(rs.getInt("releaseid"));
						switch (algorithmId) {
						case 0:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1BJubatus"));
							break;
						case 2:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1BLinearRegression"));
							break;
						case 3:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1BSVRLinear"));
							break;
						case 4:
							lastPrediction.setPredictedValueInt(rs.getInt("uc1BSVRRBF"));
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
