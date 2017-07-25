package com.nttdata.web.usecase3.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase3.dao.DefectLeakageDAO;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

@Service
public class DefectLeakageServiceImpl implements DefectLeakageService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectLeakageDAO defectLeakageDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl,int algorithmId) {

		List<Integer> oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		List<DefectLeakageModel> resultList = defectLeakageDAO.getDefectLeakageData(Integer.parseInt(userId),predictionId);

		int[][][] graphData = createDataSets(resultList, oldList, ucl, lcl);

		return graphData;
	}

	@Override
	public List<Double> predict(String predictionCode, String metricsId, String userId, int redmineProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	private int[][][] createDataSets(List<DefectLeakageModel> defectLeakageModelList, List<Integer> oldList, int[] ucl,
			int[] lcl) {

		int maxSize = defectLeakageModelList.size();
		int[][] defectLeakage = new int[maxSize][];
		int[][] predictedArray = new int[2][];
		int[][] uclValues = new int[maxSize+1][];
		int[][] lclValues = new int[maxSize+1][];
		int i=0;
		for(DefectLeakageModel defect : defectLeakageModelList)	{
			
			defectLeakage[i] = new int[]{ defect.getRelease(),defect.getDefectLeakage()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		uclValues[defectLeakageModelList.size()]=new int []{defectLeakageModelList.get(defectLeakageModelList.size()-1).getRelease()+1,ucl[0]};
		lclValues[defectLeakageModelList.size()]=new int []{defectLeakageModelList.get(defectLeakageModelList.size()-1).getRelease()+1,lcl[0]};
		predictedArray[0] = defectLeakage[defectLeakage.length-2];
		predictedArray[1] = new int[]{defectLeakageModelList.get(defectLeakageModelList.size()-1).getRelease(),oldList.get(0)};
		System.out.println(defectLeakage);
		int graphData[][][] = new int[4][][];
		graphData[0] = lclValues;
		graphData[1] = uclValues;
		graphData[2] = defectLeakage;
		graphData[3] = predictedArray;
		

		return graphData;

	}

	@Override
	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit) {
		return defectLeakageDAO.getAvgDataForUseCase3(userId, predictionCode, limit);
	}

}
