package com.nttdata.web.service;

import java.util.List;
import java.util.Map;

import com.nttdata.web.model.PredictedModel;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

public interface ETL {
	public List<Integer> interactETL(String eTLType, String predictionId, String metricsId, String userId,
			int redmineProjectId,int algorithmId);

	public List<int[]> interactETLForUclLcl(String eTLType, String predictionId, String metricsId, String userId,
			int redmineProjectId, List<Integer> defectCount);

	public Map<String, PredictedModel> interactETLForUseCase2(String eTLType, String predictionId, String metricsId,
			String userId, int redmineProjectId);
	
	public List<int[]> interactETLForUclLcl1(String eTLType, String predictionId, String metricsId,String userId, int redmineProjectId,List<DefectLeakageModel> defectCount) ;
}
