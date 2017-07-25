package com.nttdata.web.usecase3.service;

import java.util.List;
import com.nttdata.web.model.ModuleDetails;

public interface DefectLeakageService {
	
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId,int[] ucl,int[] lcl,int algorithmId);
	public List<Double> predict(String predictionCode, String metricsId, String userId, int redmineProjectId);
	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit) ;

}
