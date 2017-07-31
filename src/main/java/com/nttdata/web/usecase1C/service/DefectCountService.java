package com.nttdata.web.usecase1C.service;

public interface DefectCountService {
	int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl ,int algorithmId);

}
