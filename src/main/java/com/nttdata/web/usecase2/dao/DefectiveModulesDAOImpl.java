package com.nttdata.web.usecase2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class DefectiveModulesDAOImpl implements DefectiveModulesDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, List<Integer>> getModuleData(String moduleName,String predictionCode) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_MODULE_DETAILS;
		List<Integer> defectDensityList = new ArrayList<Integer>();
		List<Integer> defectRejectionList = new ArrayList<Integer>();
		List<Integer> defectLeakageList = new ArrayList<Integer>();
		List<Integer> releaseCountList = new ArrayList<Integer>();
		List<ModuleDetails> moduleDetailsList = jdbcTemplate.query(sqlQuery,
				new Object[] { moduleName, predictionCode }, new RowMapper<ModuleDetails>() {
					@Override
					public ModuleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ModuleDetails moduleDetails = new ModuleDetails();
						moduleDetails.setModuleName(rs.getString("module_name"));
						moduleDetails.setRelease(rs.getInt("release_version"));
						moduleDetails.setDefectDensity(rs.getInt("density"));
						moduleDetails.setDefectLeakage(rs.getInt("leakage"));
						moduleDetails.setDefectRejected(rs.getInt("rejection"));

						defectDensityList.add(moduleDetails.getDefectDensity());
						defectRejectionList.add(moduleDetails.getDefectRejected());
						defectLeakageList.add(moduleDetails.getDefectLeakage());
						releaseCountList.add(moduleDetails.getRelease());
						return moduleDetails;
					}

				});

		Map<String, List<Integer>> resultMap = new HashMap<String, List<Integer>>();
		resultMap.put("DefectDensity", defectDensityList);
		resultMap.put("DefectLeakage", defectLeakageList);
		resultMap.put("DefectRejection", defectRejectionList);
		resultMap.put("ReleaseVersion", releaseCountList);

		return resultMap;

	}
}
