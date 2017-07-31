package com.nttdata.web.usecase1D.service;



public interface FunctionalDefectCountService {

	int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl,int algorithmId);

}
