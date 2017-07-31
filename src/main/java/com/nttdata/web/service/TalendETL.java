package com.nttdata.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalendETL  {

	@Autowired
	JubatusProcessor jubatusProcessor;

/*	@Override
	public List<Integer> interactETL(String eTLType, String predictionId, String metricsId, String userId,
			int redmineProjectId) {

		if (eTLType.equalsIgnoreCase("calculateUclLcl")) {
			return retrieveUclLclData(StringUtils.valueOf(redmineProjectId));
		} else if (eTLType.equalsIgnoreCase("predictUseCase1A")) {
			return predictUseCase1A(StringUtils.valueOf(redmineProjectId));
		} else if (eTLType.equalsIgnoreCase("predictUseCase1B")) {
			return predictUseCase1B(StringUtils.valueOf(redmineProjectId));
		}
		return null;
	}

	private List<int[]> retrieveUclLclData(String projectId) {
		// TODO to change class name
		MySQLFetchDefectDensity defectDensityJob = new MySQLFetchDefectDensity();
		defectDensityJob.runJob(new String[] {});

		return jubatusProcessor.computeUclLclData(0);
	}

	private List<Integer> predictUseCase1A(String projectId) {

		MySQLFetchDefectDensity defectDensityJob = new MySQLFetchDefectDensity();
		defectDensityJob.runJob(new String[] {});
		return jubatusProcessor.predictDensity(1,"DEFECT_DENSITY");
	}

	private List<Integer> predictUseCase1B(String projectId) {
		// TODO to change class name
		MySQLFetchDefectDensity defectDensityJob = new MySQLFetchDefectDensity();
		defectDensityJob.runJob(new String[] {});
		return null;
		//return jubatusProcessor.predictDensity(1);
	}

	@Override
	public Map<String, PredictedModel> interactETLForUseCase2(String eTLType, String predictionId, String metricsId,
			String userId, int redmineProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<int[]> interactETLForUclLcl(String eTLType, String predictionId, String metricsId, String userId,*/
		/*	int redmineProjectId) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
