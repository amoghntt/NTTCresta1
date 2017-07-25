package com.nttdata.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.web.dao.ConfigDAO;
import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictedModel;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
@Service
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private ConfigDAO configDAO;

	public Map<String, String> validatePredictData(ConfigBean configBean) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (configBean.getPredictBean().getPredictionId().isEmpty()) {
			resultMap.put("predictBean.predictionId", "metricspage.predictionid.required");
		}
		return resultMap;
	}

	public Map<String, String> validateProjectListData(ConfigBean configBean) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (configBean.getProjectBean().getRedmineProjectId().toString().isEmpty()) {
			resultMap.put("predictBean.predictionId", "metricspage.predictionid.required");
		}
		return resultMap;
	}

	@Override
	public List<DefectCountModel> getDefectCountData(int userId) {
		return configDAO.getDefectCountData(userId);
	}

	@Override
	public List<PredictionBean> getPredictListForUser() {
		List<PredictionBean> predictionList = configDAO.getPredictionListForUser();
		return predictionList;
	}

	@Override
	public Map<String, List<MetricsBean>> getPredictMetricsMappingService() {
		List<MetricsBean> metricsBeanList = configDAO.getPredictMetricsMapping();
		Map<String, List<MetricsBean>> metricsBeanMap = new HashMap<String, List<MetricsBean>>();

		for (MetricsBean metricsBean : metricsBeanList) {
			String predictionCode = metricsBean.getPredictionCode();
			MetricsBean metricBeanObj = new MetricsBean();
			metricBeanObj.setLcl(metricsBean.getLcl());
			metricBeanObj.setUcl(metricsBean.getUcl());
			metricBeanObj.setMetricsId(metricsBean.getMetricsId());
			metricBeanObj.setMetricsName(metricsBean.getMetricsName());
			metricBeanObj.setWeightage(metricsBean.getWeightage());
			if (metricsBeanMap.containsKey(predictionCode)) {
				List<MetricsBean> metricsList = metricsBeanMap.get(predictionCode);
				metricsList.add(metricBeanObj);

			} else {
				List<MetricsBean> newMetricsBeanList = new ArrayList<MetricsBean>();
				newMetricsBeanList.add(metricBeanObj);
				metricsBeanMap.put(predictionCode, newMetricsBeanList);
			}
		}

		return metricsBeanMap;
	}

	@Override
	public List<int[]> processTask(String metricsId, int redmineProjectId, List<Integer> defectCount) {

		return etlBean.interactETLForUclLcl("calculateUclLcl", null, metricsId, null, redmineProjectId, defectCount);
	}

	public ETL getEtlBean() {
		return etlBean;
	}

	public void setEtlBean(ETL etlBean) {
		this.etlBean = etlBean;
	}

	@Override
	public List<String> getAxesLabel(int metricsId) {

		List<String> axesLabel = new ArrayList<>();
		axesLabel.add("Release");
		axesLabel.add("Defect Density");
		axesLabel.add("Defect Leakage");
		return axesLabel;
	}

	@Override
	public List<DefectLeakageModel> getDefectDensityData(int userid) {
		return configDAO.getDefectDensityData(userid);
	}
	
	@Override
	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceDataTelephonica(int userId) {
		return configDAO.getDefectAcceptanceDataTelephonica(userId);
	}
	
	@Override
	public List<Integer> getDefectDeferralCountTelephonica(int userid, String predictionCode) {
		return configDAO.getDefectDeferralCount(userid, predictionCode);
	}

	@Override
	public List<Integer> getDefectAcceptanceCountTelephonica(int userid, String predictionCode) {
		return configDAO.getDefectAcceptanceCountTelephonica(userid, predictionCode);
	}

	@Override
	public List<Integer> getDefectDensityCountTelephonica(int userid, String predictionCode) {
		return configDAO.getDefectDensityCountTelephonica(userid, predictionCode);
	}
	
	@Override
	public List<Integer> getDefectModuleCount(int userid, String predictionCode,Integer metricsId) {
		return configDAO.getDefectModuleCount(userid, predictionCode,metricsId);
	}


	@Override
	public List<Integer> getFunctionalDefectDensityCountTelephonica(int userid, String predictionCode) {
		return configDAO.getFunctionalDefectDensityCountTelephonica(userid, predictionCode);
	}

	@Override
	public List<Integer> getDefectCountTelephonica(int userid, String predictionCode) {
		return configDAO.getDefectCountTelephonica(userid, predictionCode);
	}

	@Override
	public List<DefectDeferralModel> getDefectDeferralData(int userid, String pred_code) {
		return configDAO.getDefectDeferralData(userid, pred_code);
	}
	@Override
	public List<DefectDeferralModelTelephonica> getDefectDeferralTelephonicaData(int userid, String pred_code) {
		return configDAO.getDefectDeferralTelephonicaData(userid, pred_code);
	}

	@Override
	public Map<String, PredictedModel> processTaskForUseCase2(String useCaseType, String predictionId, String metricsId,
			String userId, int redmineProjectId) {
		return etlBean.interactETLForUseCase2(useCaseType, predictionId, metricsId, userId, redmineProjectId);
	}

	@Override
	public Map<String, List<ModuleDetails>> getModuleWiseData(int userid, String predictionCode) {

		return configDAO.getModuleWiseData(userid, predictionCode);
	}

	@Override
	public List<DefectLeakageModel> getDefectLeakageData(int userid,String predictionCode) {
		// TODO Auto-generated method stub
		return configDAO.getDefectLeakageData(userid,predictionCode);
	}

	@Override
	public List<ModuleDetails> getAvgData(int userId, String predictionCode, String moduleName, int limit) {
		return configDAO.getAvgData(userId, predictionCode, moduleName, limit);
	}

	@Override
	public List<ModuleDetails> getAvgDataForDefectDesnity(int userId, String predictionCode, int limit) {
		return configDAO.getAvgDataForDefectDesnity(userId, predictionCode, limit);
	}

	@Override
	public List<ModuleDetails> getAvgDataForDefectDeferral(int userId, String predictionCode, int limit) {
		return configDAO.getAvgDataForDefectDeferral(userId, predictionCode, limit);
	}

	@Override
	public List<Integer> getReleaseDataForUseCase3(String userId, String predictionCode) {
		return configDAO.getReleaseDataForUseCase3(userId, predictionCode);
	}

	@Override
	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit) {
		return configDAO.getAvgDataForUseCase3(userId, predictionCode, limit);
	}

	@Override
	public List<FunctionalDefectCountModel> getFunctionalDefectData(int userId) {

		return configDAO.getFunctionalDefectData(userId);

	}

	@Override
	public List<DefectDensityModelTelephonica> getDefectDensityDataForUseCase1Telephonica(int userid) {
		return configDAO.getDefectDensityDataForUseCase1Telephonica(userid);
	}

	
}
