package com.nttdata.web.usecase3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class DefectLeakageDAOImpl implements DefectLeakageDAO {
	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DefectLeakageModel> getDefectLeakageData(int userid,String predictionCode) {
		

		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_LEAKAGE_DATA;

		List<DefectLeakageModel> defectDensityModelList = jdbcTemplate.query(sqlQuery, new Object[] { userid, predictionCode },
				new RowMapper<DefectLeakageModel>() {
					@Override
					public DefectLeakageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectLeakageModel defectDensityModel = new DefectLeakageModel();
						defectDensityModel.setRelease(rs.getInt("release_version"));
						defectDensityModel.setDefectDensity(rs.getInt("density"));
						defectDensityModel.setDefectLeakage(rs.getInt("leakage"));
						defectDensityModel.setDefectRejected(rs.getInt("rejection"));
						return defectDensityModel;
					}
				});
		return defectDensityModelList;
	}

	@Override
	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_USECASE3_AVG_DATA;

		List<ModuleDetails> moduleDetailsList = jdbcTemplate.query(sqlQuery,
				new Object[] { userId, predictionCode, limit }, new RowMapper<ModuleDetails>() {
					@Override
					public ModuleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ModuleDetails moduleDetails = new ModuleDetails();
						moduleDetails.setRelease(rs.getInt("releaseVersionMAX"));
						moduleDetails.setDefectDensity(rs.getInt("densityAVG"));
						moduleDetails.setDefectLeakage(rs.getInt("leakageAVG"));
						moduleDetails.setDefectRejected(rs.getInt("rejectionAVG"));

						return moduleDetails;
					}
				});
		return moduleDetailsList;
	}

}
