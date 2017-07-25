package com.nttdata.web.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.nttdata.web.model.AlgorithmBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.ProjectBean;

public interface CommonService {

	public String getProjectName(Integer redmineProjectId, List<ProjectBean> projectList);

	public String getPredictionName(String predictionCode, List<PredictionBean> predictionList);

	public void deleteDataFromTempDensitytable(int userId, String predictionCode);
	
	public String convertToJSON(int[][][] graphData) ;
	
	public void saveData(MetricsBean metricsBean);
	public List<AlgorithmBean> getAlgorithmListForUser();

	String convertToJSON(HashMap<String, Integer> resultMap);
	
	public String convertToJSON(List<LinkedHashMap<String, Integer>> graphData);
	
}
