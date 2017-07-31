package com.nttdata.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.usecase1.model.DefectDensityModel;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModel;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
import com.nttdata.web.utils.CrestaQueryConstants;

import routines.system.StringUtils;

@Repository
@Transactional(readOnly = true)
public class ConfigDAOImpl implements ConfigDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfigDAOImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PredictionBean> getPredictionListForUser() {
		String sqlQuery = CrestaQueryConstants.QRY_GET_PREDICT_LIST;
		List<PredictionBean> predictList = jdbcTemplate.query(sqlQuery, new Object[] {},
				new RowMapper<PredictionBean>() {

					@Override
					public PredictionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						PredictionBean predictBean = new PredictionBean();
						predictBean.setPredictionId(rs.getString("pred_code"));
						predictBean.setPredictDescription(rs.getString("pred_description"));

						return predictBean;
					}
				});

		return predictList;
	}

	@Override
	public List<MetricsBean> getPredictMetricsMapping() {
		log.debug("ConfigDAOImpl : getMetricsList() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_METRICS_LIST;

		List<MetricsBean> metricsBeanList = jdbcTemplate.query(sqlQuery, new Object[] {}, new RowMapper<MetricsBean>() {
			@Override
			public MetricsBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				MetricsBean metricsBean = new MetricsBean();
				int[] ucl = new int[2];
				int[] lcl = new int[2];
				metricsBean.setMetricsId(rs.getInt("metrics_id"));
				ucl[0] = rs.getInt("ucl");
				lcl[0] = rs.getInt("lcl");
				metricsBean.setLcl(lcl);
				metricsBean.setUcl(ucl);
				metricsBean.setMetricsName(rs.getString("metrics"));
				metricsBean.setPredictionId(rs.getString("id"));
				metricsBean.setPredictionCode(rs.getString("pred_code"));
				metricsBean.setWeightage(rs.getDouble("weightage"));
				return metricsBean;
			}
		});
		return metricsBeanList;
	}

	/*
	 * @Override public void deleteDataFromTempDensitytable(int userId, String
	 * predictionCode) {
	 * 
	 * String sqlQuery = ""; if (predictionCode.equals("DEFECT_DENSITY")) {
	 * sqlQuery = CrestaQueryConstants.QRY_DELETE_DATA; } else if
	 * (predictionCode.equals("DEFECTIVE_MODULES")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE2_DELETE_DATA; }
	 * 
	 * else if (predictionCode.equals("DEFECT_LEAKAGE")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE3_DELETE_DATA; } else if
	 * (predictionCode.equals("DEFECT_ACCEPTANCE_RATE")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE1A_DELETE_DATA;
	 * 
	 * } else if (predictionCode.equals("FUNCTIONAL_DEFECT_COUNT")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE1D_DELETE_DATA; } else if
	 * (predictionCode.equals("DEFECT_DEFERRAL_RATE")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE1B_DELETE_DATA; } else if
	 * (predictionCode.equals("DEFECT_COUNT")) { sqlQuery =
	 * CrestaQueryConstants.QRY_USECASE1C_DELETE_DATA; }
	 * 
	 * Object[] params = { userId, predictionCode }; // define SQL types of the
	 * arguments int[] types = { Types.INTEGER, Types.VARCHAR };
	 * 
	 * jdbcTemplate.update(sqlQuery, params, types);
	 * 
	 * }
	 */

	private int getMetricsMappingId(int metricsId, String predictionId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_METRICS_ID;

		List<Integer> mappingId = new ArrayList<Integer>();

		mappingId = jdbcTemplate.query(sqlQuery, new Object[] { metricsId, predictionId }, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int mapId = rs.getInt("id");

				return mapId;
			}

		});

		return mappingId.get(0);
	}

	@Override
	public void saveData(MetricsBean metricsBean) {
		String sqlQuery = CrestaQueryConstants.QRY_UPDATE_DATA;
		int metricsMapping = getMetricsMappingId(metricsBean.getMetricsId(), metricsBean.getPredictionId());
		int[] ucl = metricsBean.getUcl();
		int[] lcl = metricsBean.getLcl();
		Object[] params = { ucl[0], lcl[0], ucl[1], lcl[1], metricsBean.getWeightage(), metricsMapping };
		// define SQL types of the arguments
		int[] types = { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE, Types.INTEGER };

		jdbcTemplate.update(sqlQuery, params, types);
	}

	@Override
	public List<DefectLeakageModel> getDefectDensityData(int userid) {
		log.debug("ConfigDAOImpl : getDefectDensityData() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA;

		List<DefectLeakageModel> defectDensityModelList = jdbcTemplate.query(sqlQuery, new Object[] { userid },
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
	public List<DefectCountModel> getDefectCountData(int userid) {
		log.debug("ConfigDAOImpl : getDefectDensityData() : call ");
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

						return defectCountModel;
					}
				});
		return defectCountModelList;
	}

	@Override
	public List<Integer> getDefectDeferralCount(int userid, String predictionCode) {
		log.debug("ConfigDAOImpl : getDefectDeferralCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DEFERRAL_DATA;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid, predictionCode },
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Integer defectCount = null;
						defectCount = rs.getInt("defect_deferral_rate");

						return defectCount;
					}
				});
		return defectCountList;
	}

	@Override
	public List<Integer> getDefectAcceptanceCount(int userid, String predictionCode) {
		log.debug("ConfigDAOImpl : getDefectAcceptanceCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_ACCEPTANCE;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer defectCount = null;
				defectCount = rs.getInt("acceptance");

				return defectCount;
			}
		});
		return defectCountList;
	}

	@Override
	public List<Integer> getDefectDensityCount(int userid, String predictionCode) {
		log.debug("ConfigDAOImpl : getDefectDensityCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DATA;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid, predictionCode },
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Integer defectCount = null;
						defectCount = rs.getInt("defect_count") / rs.getInt("KLOC");

						return defectCount;
					}
				});
		return defectCountList;
	}

	@Override
	public List<Integer> getDefectModuleCount(int userid, String predictionCode, Integer metricsId) {
		log.debug("ConfigDAOImpl : getDefectModuleCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_MODULE_LIST;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid, predictionCode },
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Integer defectCount = null;
						if (metricsId == 1) {
							defectCount = rs.getInt("density");
						} else if (metricsId == 2) {
							defectCount = rs.getInt("leakage");
						} else if (metricsId == 3) {
							defectCount = rs.getInt("rejection");
						}

						return defectCount;
					}
				});
		return defectCountList;
	}

	@Override
	public List<Integer> getFunctionalDefectDensityCount(int userid, String predictionCode) {
		log.debug("ConfigDAOImpl : getFunctionalDefectDensityCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_FUNCTIONAL_DEFECT_COUNT;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer defectCount = null;
				defectCount = rs.getInt("defect_count");

				return defectCount;
			}
		});
		return defectCountList;
	}

	@Override
	public List<Integer> getDefectCount(int userid, String predictionCode) {
		log.debug("ConfigDAOImpl : getDefectDensityCount() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_COUNT_DATA;

		List<Integer> defectCountList = jdbcTemplate.query(sqlQuery, new Object[] { userid }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer defectCount = null;
				defectCount = rs.getInt("defect_count");

				return defectCount;
			}
		});
		return defectCountList;
	}

	@Override
	public List<DefectDeferralModel> getDefectDeferralData(int userid, String pred_code) {
		log.debug("ConfigDAOImpl : getDefectDensityData() : call ");
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
						// defectDeferralModel.setFeasibilityWithinMilestone(rs.getBoolean("feasibility_within_milestone"));
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
	public List<DefectDeferralModelTelephonica> getDefectDeferralTelephonicaData(int userid, String pred_code) {
		log.debug("ConfigDAOImpl : getDefectDensityData() : call ");
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1B_TELEPHONICA;

		List<DefectDeferralModelTelephonica> defectDeferralModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { userid}, new RowMapper<DefectDeferralModelTelephonica>() {
					@Override
					public DefectDeferralModelTelephonica mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDeferralModelTelephonica defectDeferralModel = new DefectDeferralModelTelephonica();
						defectDeferralModel.setDefectDeferralCount(rs.getInt("defect_deferral_rate"));
						defectDeferralModel.setBg_Severity(rs.getInt("bg_severity"));
						defectDeferralModel.setBg_Priority(rs.getInt("bg_priority"));
						defectDeferralModel.setEffortToFixDefect(rs.getInt("effort_to_fix_defect"));
						defectDeferralModel.setCostToFixDefect(rs.getInt("cost_to_fix_defect"));
						defectDeferralModel.setImpactOfDefectFix(rs.getInt("impact_of_defect_fix"));
						// defectDeferralModel.setFeasibilityWithinMilestone(rs.getBoolean("feasibility_within_milestone"));
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
	public List<DefectLeakageModel> getDefectLeakageData(int userid, String predictionCode) {

		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_LEAKAGE_DATA;

		List<DefectLeakageModel> defectDensityModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { userid, predictionCode }, new RowMapper<DefectLeakageModel>() {
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
	public Map<String, List<ModuleDetails>> getModuleWiseData(int userid, String predictionCode) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_MODULE_LIST;

		Map<String, List<ModuleDetails>> moduleDetailsMap = new HashMap<String, List<ModuleDetails>>();
		List<ModuleDetails> moduleDetailsList = jdbcTemplate.query(sqlQuery, new Object[] { userid, predictionCode },
				new RowMapper<ModuleDetails>() {
					@Override
					public ModuleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						ModuleDetails moduleDetails = new ModuleDetails();
						moduleDetails.setModuleName(rs.getString("module_name"));
						moduleDetails.setRelease(rs.getInt("release_version"));
						moduleDetails.setDefectDensity(rs.getInt("density"));
						moduleDetails.setDefectLeakage(rs.getInt("leakage"));
						moduleDetails.setDefectRejected(rs.getInt("rejection"));
						if (moduleDetailsMap.containsKey(moduleDetails.getModuleName())) {

							List<ModuleDetails> moduleDetailsList = moduleDetailsMap.get(moduleDetails.getModuleName());
							moduleDetailsList.add(moduleDetails);
						} else {
							List<ModuleDetails> moduleDetailsList = new ArrayList<ModuleDetails>();
							moduleDetailsList.add(moduleDetails);
							moduleDetailsMap.put(moduleDetails.getModuleName(), moduleDetailsList);
						}
						return moduleDetails;
					}

				});

		return moduleDetailsMap;
	}

	@Override
	public Map<String, List<Integer>> getModuleDetails(String moduleName, String predictionCode) {
		// TODO Auto-generated method stub
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

	@Override
	public List<ModuleDetails> getAvgData(int userId, String predictionCode, String moduleName, int limit) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_AVG_DATA;

		List<ModuleDetails> moduleDetailsList = jdbcTemplate.query(sqlQuery,
				new Object[] { userId, predictionCode, moduleName, limit }, new RowMapper<ModuleDetails>() {
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

	@Override
	public List<ModuleDetails> getAvgDataForDefectDesnity(int userId, String predictionCode, int limit) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_AVG_DATA_DEFECT_DENSITY;

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

	@Override
	public List<ModuleDetails> getAvgDataForDefectDeferral(int userId, String predictionCode, int limit) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_AVG_DATA_DEFECT_DENSITY;

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

	@Override
	public List<Integer> getReleaseDataForUseCase3(String userId, String predictionCode) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_USECASE3_RELEASE_DATA;
		List<Double> releaseList = new ArrayList<Double>();
		List<Integer> releaseListToReturn = new ArrayList<Integer>();
		try {
			releaseList = jdbcTemplate.query(sqlQuery, new Object[] { Integer.parseInt(userId), predictionCode },
					new RowMapper<Double>() {

						@Override
						public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
							double release = Double.parseDouble(StringUtils.valueOf(rs.getInt("release_version")));

							return release;
						}

					});
		} catch (DataAccessException e) {

			e.printStackTrace();
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
		for (Double releaseCount : releaseList) {
			releaseListToReturn.add((int) Math.round(releaseCount));
		}
		return releaseListToReturn;
	}

	@Override
	public List<FunctionalDefectCountModel> getFunctionalDefectData(int userid) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_FUNCTIONAL_DEFECT_COUNT;
		List<FunctionalDefectCountModel> functionalDefectCountModelList = jdbcTemplate.query(sqlQuery,
				new Object[] { userid }, new RowMapper<FunctionalDefectCountModel>() {
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
	public List<DefectDensityModel> getDefectDensityDataForUseCase1(int userid) {

		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1;
		List<DefectDensityModel> defectDensityModel = jdbcTemplate.query(sqlQuery, new Object[] { userid },
				new RowMapper<DefectDensityModel>() {
					@Override
					public DefectDensityModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDensityModel defectDensityModel = new DefectDensityModel();

						defectDensityModel.setRelease((rs.getInt("release_version")));
						defectDensityModel.setDefectDensity(Math.round(rs.getInt("defect_count") / rs.getInt("KLOC")));
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
		return defectDensityModel;

	}
	
	@Override
	public List<DefectDensityModelTelephonica> getDefectDensityDataForUseCase1Telephonica(int userid) {

		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1_TELEPHONICA;
		List<DefectDensityModelTelephonica> defectDensityModelTelephonica = jdbcTemplate.query(sqlQuery, new Object[] { userid },
				new RowMapper<DefectDensityModelTelephonica>() {
					@Override
					public DefectDensityModelTelephonica mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectDensityModelTelephonica defectDensityModelTelephonica = new DefectDensityModelTelephonica();
						defectDensityModelTelephonica.setRel_Id(rs.getInt("rel_id"));
						defectDensityModelTelephonica.setDefectCount(rs.getInt("defect_count"));			
						defectDensityModelTelephonica.setDefectDensity(Math.round(rs.getInt("defect_count") / rs.getInt("KLOC")));
						defectDensityModelTelephonica.setKiloLinesOfCode(rs.getInt("KLOC"));
						defectDensityModelTelephonica.setTestCaseCount(rs.getInt("test_case_count"));
						defectDensityModelTelephonica.setApplicationComplexity(rs.getInt("application_complexity"));
						defectDensityModelTelephonica.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectDensityModelTelephonica.setTechnicalSkills(rs.getInt("technical_skills"));
						defectDensityModelTelephonica.setRequirementQueryCount(rs.getInt("requirements_query_count"));
						defectDensityModelTelephonica.setCodeReviewComments((rs.getInt("code_review_comments")));
						defectDensityModelTelephonica.setDesignReviewComments((rs.getInt("design_review_comments")));
						defectDensityModelTelephonica.setBg_Severity(rs.getInt("bg_severity"));
						defectDensityModelTelephonica.setBg_Priority(rs.getInt("bg_priority"));
						return defectDensityModelTelephonica;
					}
				});
		return defectDensityModelTelephonica;

	}
	
	

	public List<DefectAcceptanceModel> getDefectAcceptanceData(int userid) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_ACCEPTANCE;
		List<DefectAcceptanceModel> defectAcceptanceModelList = jdbcTemplate.query(sqlQuery, new Object[] { userid },
				new RowMapper<DefectAcceptanceModel>() {
					@Override
					public DefectAcceptanceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectAcceptanceModel defectAcceptanceModel = new DefectAcceptanceModel();

						defectAcceptanceModel.setRelease((rs.getInt("release_version")));
						defectAcceptanceModel.setDefectAcceptance((rs.getInt("acceptance")));
						defectAcceptanceModel.setKiloLinesOfCode(rs.getInt("KLOC"));
						defectAcceptanceModel.setTestCaseCount(rs.getInt("test_case_count"));
						defectAcceptanceModel.setApplicationComplexity(rs.getInt("application_complexity"));
						defectAcceptanceModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectAcceptanceModel.setTechnicalSkills(rs.getInt("technical_skills"));
						defectAcceptanceModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
						defectAcceptanceModel.setCodeReviewComments((rs.getInt("code_review_comments")));
						defectAcceptanceModel.setDesignReviewComments((rs.getInt("design_review_comments")));
						return defectAcceptanceModel;
					}
				});
		return defectAcceptanceModelList;
	}
	
	
	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceDataTelephonica(int userid) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1A_TELEPHONICA;
		List<DefectAcceptanceModelTelephonica> defectAcceptanceModelList = jdbcTemplate.query(sqlQuery, new Object[] { userid },
				new RowMapper<DefectAcceptanceModelTelephonica>() {
					@Override
					public DefectAcceptanceModelTelephonica mapRow(ResultSet rs, int rowNum) throws SQLException {
						DefectAcceptanceModelTelephonica defectAcceptanceModelTelephonica = new DefectAcceptanceModelTelephonica();

						defectAcceptanceModelTelephonica.setRelease((rs.getInt("rel_id")));
						defectAcceptanceModelTelephonica.setDefectAcceptance((rs.getInt("acceptance")));
						defectAcceptanceModelTelephonica.setKiloLinesOfCode(rs.getInt("KLOC"));
						defectAcceptanceModelTelephonica.setTestCaseCount(rs.getInt("test_case_count"));
						defectAcceptanceModelTelephonica.setApplicationComplexity(rs.getInt("application_complexity"));
						defectAcceptanceModelTelephonica.setDomainKnowledge(rs.getInt("domain_knowledge"));
						defectAcceptanceModelTelephonica.setTechnicalSkills(rs.getInt("technical_skills"));
						defectAcceptanceModelTelephonica.setRequirementQueryCount(rs.getInt("requirements_query_count"));
						defectAcceptanceModelTelephonica.setCodeReviewComments((rs.getInt("code_review_comments")));
						defectAcceptanceModelTelephonica.setDesignReviewComments((rs.getInt("design_review_comments")));
						return defectAcceptanceModelTelephonica;
					}
				});
		return defectAcceptanceModelList;
	}
	
	

}
