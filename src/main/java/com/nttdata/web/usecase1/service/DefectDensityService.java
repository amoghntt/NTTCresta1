package com.nttdata.web.usecase1.service;

public interface DefectDensityService {
	int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl, int algorithmId);
}
