package com.nttdata.web.usecase1A.service;

public interface DefectAcceptanceService {
	int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl,int algorithmId);
}
