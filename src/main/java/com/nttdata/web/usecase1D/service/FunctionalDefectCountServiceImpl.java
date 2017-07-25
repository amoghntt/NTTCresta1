package com.nttdata.web.usecase1D.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase1D.dao.FunctionalDefectCountDAO;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;

@Service
public class FunctionalDefectCountServiceImpl implements FunctionalDefectCountService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private FunctionalDefectCountDAO functionalDefectCountDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl,int algorithmId) {
		List<Integer> oldList = new ArrayList<Integer>();
			oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		List<FunctionalDefectCountModel> resultList = functionalDefectCountDAO
				.getFunctionalDefectCountAndReleaseDataTelephonica(userId);
		List<FunctionalDefectCountModel> resultList1 = functionalDefectCountDAO.getAllData(userId);
		List<ReleaseDetails> lastTenPredictedValues =functionalDefectCountDAO.getLastTenPredictions(algorithmId);

		int[][][] graphData = createDataSetsTelephonica(resultList1, oldList, ucl, lcl,algorithmId,lastTenPredictedValues);

		return graphData;
	}

	private int[][][] createDataSetsTelephonica(List<FunctionalDefectCountModel> functionalDefectCountModelList,
			List<Integer> oldList, int[] ucl, int[] lcl,int algorithmId,List<ReleaseDetails> lastTenPredictedValues) {
		int maxSize = functionalDefectCountModelList.size();
		int[][] functionalDefects = new int[maxSize][];
		int [][] lastTenPredictedArray = new int [lastTenPredictedValues.size()][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize + 1][];
		int[][] lclValues = new int[maxSize + 1][];
		int i = 0;
		int j = 0;
		if(algorithmId != 1){
		for (ReleaseDetails lastPrediction : lastTenPredictedValues){
			lastTenPredictedArray[j] = new int []{lastPrediction.getReleaseid(),lastPrediction.getPredictedValueInt()};
			j++;
		}}
		for(FunctionalDefectCountModel defect : functionalDefectCountModelList)	{
			
			functionalDefects[i] = new int[]{ defect.getRelease(),defect.getDefectCount()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		uclValues[functionalDefectCountModelList.size()] = new int[] {
				functionalDefectCountModelList.get(functionalDefectCountModelList.size() - 1).getRelease() + 1, ucl[0] };
		lclValues[functionalDefectCountModelList.size()] = new int[] {
				functionalDefectCountModelList.get(functionalDefectCountModelList.size() - 1).getRelease() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = functionalDefects[functionalDefects.length-1];
		predictedArrayLinearRegression[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease()+1,oldList.get(0)};
		System.out.println(functionalDefects);
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = functionalDefects[functionalDefects.length-1];
			predictedArraySVRLinearKernel[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease() + 1,oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = functionalDefects[functionalDefects.length-1];
			predictedArraySVRRBFKernel[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease() + 1,oldList.get(2)};
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = functionalDefects;
			graphData[3] = predictedArrayLinearRegression;
			graphData[4] = predictedArraySVRLinearKernel;
			graphData[5] = predictedArraySVRRBFKernel;
			return graphData;
		}
		else
		{
		
		int graphData[][][] = new int[4][][];
		graphData[0] = lclValues;
		graphData[1] = uclValues;
		graphData[2] = functionalDefects;
		//graphData[3] = predictedArrayLinearRegression;
		graphData[3] = lastTenPredictedArray ;
		return graphData;
		}

		

	}
	

}
