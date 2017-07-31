package com.nttdata.web.usecase1B.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase1B.dao.DefectDefferalDAO;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;

@Service
public class DefectDefferalServiceImpl implements DefectDefferalService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectDefferalDAO defectDefferalDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl, int algorithmId) {
		List<Integer> oldList = new ArrayList<Integer>();
	
			oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		//List<DefectDeferralModel> resultList = defectDefferalDAO.getDefectDefferalAndReleaseData(userId, predictionId);
			List<DefectDeferralModelTelephonica> resultList = defectDefferalDAO.getDefectDefferalAndReleaseTelephonicaData(userId, predictionId);

			List<ReleaseDetails> lastTenPredictedValues = defectDefferalDAO.getLastTenPredictions();

		int[][][] graphData = createDataSetsTelephonica(resultList, oldList, ucl, lcl,algorithmId, lastTenPredictedValues);

		return graphData;
	}

	private int[][][] createDataSets(List<DefectDeferralModel> defectDeferralList, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId) {

		int maxSize = defectDeferralList.size();
		int[][] defectDeferral = new int[maxSize][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize][];
		int[][] lclValues = new int[maxSize][];
		int i = 0;
		for (DefectDeferralModel defect : defectDeferralList) {

			defectDeferral[i] = new int[] { defect.getReleaseVersion(), defect.getDefectDeferralCount() };
			uclValues[i] = new int[] { defect.getReleaseVersion(), ucl[0] };
			lclValues[i] = new int[] { defect.getReleaseVersion(), lcl[0] };
			i++;
		}

		uclValues[defectDeferralList.size()] = new int[] {
				defectDeferralList.get(defectDeferralList.size() - 1).getReleaseVersion() + 1, ucl[0] };
		lclValues[defectDeferralList.size()] = new int[] {
				defectDeferralList.get(defectDeferralList.size() - 1).getReleaseVersion() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = defectDeferral[defectDeferral.length - 2];
		predictedArrayLinearRegression[1] = new int[] { defectDeferralList.get(defectDeferralList.size() - 1).getReleaseVersion(),
				oldList.get(0) };
		System.out.println(defectDeferral);
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectDeferral[defectDeferral.length - 2];
			predictedArraySVRLinearKernel[1] = new int[] { defectDeferralList.get(defectDeferralList.size() - 1).getReleaseVersion(),
				oldList.get(1) };
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectDeferral[defectDeferral.length - 2];
			predictedArraySVRRBFKernel[1] = new int[] { defectDeferralList.get(defectDeferralList.size() - 1).getReleaseVersion(),
				oldList.get(2) };
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectDeferral;
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
			graphData[2] = defectDeferral;
			graphData[3] = predictedArrayLinearRegression;
			return graphData;
			
		}
	
	}
	
	private int[][][] createDataSetsTelephonica(List<DefectDeferralModelTelephonica> defectDeferralList, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId,List<ReleaseDetails> lastTenPredictedValues) {

		int maxSize = defectDeferralList.size();
		int[][] defectDeferral = new int[maxSize][];
		int [][] lastTenPredictedArray = new int [lastTenPredictedValues.size()][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize + 1][];
		int[][] lclValues = new int[maxSize + 1][];
		int i = 0;
		int j = 0;
		for (ReleaseDetails lastPrediction : lastTenPredictedValues){
			lastTenPredictedArray[j] = new int []{lastPrediction.getReleaseid(),lastPrediction.getPredictedValueInt()};
			j++;
		}
		for (DefectDeferralModelTelephonica defect : defectDeferralList) {

			defectDeferral[i] = new int[] { defect.getRel_Id(), defect.getDefectDeferralCount() };
			uclValues[i] = new int[] { defect.getRel_Id(), ucl[0] };
			lclValues[i] = new int[] { defect.getRel_Id(), lcl[0] };
			i++;
		}
		uclValues[defectDeferralList.size()] = new int[] {defectDeferralList.get(defectDeferralList.size() - 1).getRel_Id() + 1 , ucl[0] };
		lclValues[defectDeferralList.size()] = new int[] {defectDeferralList.get(defectDeferralList.size() - 1).getRel_Id() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = defectDeferral[defectDeferral.length - 1];
		predictedArrayLinearRegression[1] = new int[] {
				defectDeferralList.get(defectDeferralList.size() - 1).getRel_Id() + 1, oldList.get(0) };
		System.out.println(defectDeferral);

		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectDeferral[defectDeferral.length - 2];
			predictedArraySVRLinearKernel[1] = new int[] { defectDeferralList.get(defectDeferralList.size() - 1).getRel_Id(),
				oldList.get(1) };
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectDeferral[defectDeferral.length - 2];
			predictedArraySVRRBFKernel[1] = new int[] { defectDeferralList.get(defectDeferralList.size() - 1).getRel_Id(),
				oldList.get(2) };
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectDeferral;
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
			graphData[2] = defectDeferral;
			//graphData[3] = predictedArrayLinearRegression;
			graphData[3] = lastTenPredictedArray;
			return graphData;
			
		}
	
	}
}
