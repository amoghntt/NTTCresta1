package com.nttdata.web.service;

import java.util.List;

import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

public interface CalculateUCLLCLService {
	MetricsBean processCalculateUclLcl(int projectId, String metricsId, List<Integer> defectCount);

	MetricsBean processCalculateUclLcl(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, List<Integer> defectCount);

	public MetricsBean getDefectCountMetricsBean(Integer userId, String predictionCode, Integer metricsId,
			Integer projectId);

	public MetricsBean processCalculateUclLcl1(int projectId, String metricsId, List<DefectLeakageModel> defectCount);
	
	public MetricsBean processCalculateUclLcl1(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId,List<DefectLeakageModel> defectCount);
}
