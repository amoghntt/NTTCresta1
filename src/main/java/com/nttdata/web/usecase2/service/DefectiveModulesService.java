package com.nttdata.web.usecase2.service;

import java.util.List;
import java.util.Map;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictedModel;

public interface DefectiveModulesService {
	List<List<ModuleDetails>> processTask(String predictionCode, String metricsId, String userId, int redmineProjectId,List<MetricsBean> metricsList);
	List<Double> predict(String predictionCode, String metricsId, String userId, int redmineProjectId);
	List<List<ModuleDetails>> getModuleDisplayList (Map<String, PredictedModel> moduleWiseData,List<MetricsBean> metricsList);
	List<String> getAxesLabel();
	 List<int[][][]> getModuleDetails(String moduleName, String predictionCode, int userId,
			List<MetricsBean> metricsBeanList, int[] predictedValue);

}
