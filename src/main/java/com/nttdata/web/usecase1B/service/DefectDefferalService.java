package com.nttdata.web.usecase1B.service;

public interface DefectDefferalService {
	int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl, int algorithmId);
}
