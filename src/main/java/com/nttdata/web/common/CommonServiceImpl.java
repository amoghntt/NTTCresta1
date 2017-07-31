package com.nttdata.web.common;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.web.model.AlgorithmBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.ProjectBean;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	public String convertToJSON(int[][][] graphData) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		
		try {

			jsonInString = mapper.writeValueAsString(graphData);
			//jsonInString = jsonInString.substring(1);
			System.out.println(jsonInString);
			

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	@Override
	public String getProjectName(Integer redmineProjectId, List<ProjectBean> projectList) {
		for (ProjectBean projectBean : projectList) {
			if (projectBean.getRedmineProjectId() == redmineProjectId) {
				return projectBean.getProjectName();
			}
		}
		return null;

	}

	@Override
	public String getPredictionName(String predictionCode, List<PredictionBean> predictionList) {
		for (PredictionBean predictionBean : predictionList) {
			if (predictionBean.getPredictionId().equals(predictionCode)) {
				return predictionBean.getPredictDescription();
			}
		}
		return null;
	}

	@Override
	public void deleteDataFromTempDensitytable(int userId, String predictionCode) {
		commonDAO.deleteDataFromTempDensitytable(userId, predictionCode);
	}
	
	@Override
	public void saveData(MetricsBean metricsBean) {
		commonDAO.saveData(metricsBean);
	}
	
	@Override
	public List<AlgorithmBean> getAlgorithmListForUser() {
		return commonDAO.getAlgorithmListForUser();
	}

	@Override
	public String convertToJSON(HashMap<String, Integer> resultMap) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		
		try {

			jsonInString = mapper.writeValueAsString(resultMap);
			//jsonInString = jsonInString.substring(1);
			System.out.println(jsonInString);
			

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

}
