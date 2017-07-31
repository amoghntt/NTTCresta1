package com.nttdata.web.usecase1A.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase1A.dao.DefectAcceptanceDAO;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModel;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;

@Service
public class DefectAcceptanceServiceImpl implements DefectAcceptanceService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectAcceptanceDAO defectAcceptanceDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl,int algorithmId) {
		List<Integer> oldList = new ArrayList<Integer>();
	
				oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		List<DefectAcceptanceModelTelephonica> resultList = defectAcceptanceDAO.getDefectAcceptanceAndReleaseDataTelephonica(userId);
		
		List<ReleaseDetails> lastTenPredictedValues = defectAcceptanceDAO.getLastTenPredictions();

		int[][][] graphData = createDataSetsTelephonica(resultList, oldList, ucl, lcl,algorithmId, lastTenPredictedValues);

		return graphData;
	}

	private int[][][] createDataSets(List<DefectAcceptanceModel> defectAcceptanceModelList, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId) {

		int maxSize = defectAcceptanceModelList.size();
		int[][] defectAcceptance = new int[maxSize][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize + 1][];
		int[][] lclValues = new int[maxSize + 1][];
		int i=0;
		for(DefectAcceptanceModel defect : defectAcceptanceModelList)	{
			
			defectAcceptance[i] = new int[]{ defect.getRelease(),defect.getDefectAcceptance()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		uclValues[defectAcceptanceModelList.size()] = new int[] {
				defectAcceptanceModelList.get(defectAcceptanceModelList.size() - 1).getRelease() + 1, ucl[0] };
		lclValues[defectAcceptanceModelList.size()] = new int[] {
				defectAcceptanceModelList.get(defectAcceptanceModelList.size() - 1).getRelease() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = defectAcceptance[defectAcceptance.length-2];
		predictedArrayLinearRegression[1] = new int[]{defectAcceptanceModelList.get(defectAcceptanceModelList.size()-1).getRelease(),oldList.get(0)};
		System.out.println(defectAcceptance);
		
		
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectAcceptance[defectAcceptance.length-2];
			predictedArraySVRLinearKernel[1] = new int[]{defectAcceptanceModelList.get(defectAcceptanceModelList.size()-1).getRelease(),oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectAcceptance[defectAcceptance.length-2];
			predictedArraySVRRBFKernel[1] = new int[]{defectAcceptanceModelList.get(defectAcceptanceModelList.size()-1).getRelease(),oldList.get(2)};
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectAcceptance;
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
			graphData[2] = defectAcceptance;
			graphData[3] = predictedArrayLinearRegression;
			return graphData;
			
		}

	}
	
	
	private int[][][] createDataSetsTelephonica(List<DefectAcceptanceModelTelephonica> defectAcceptanceModelListTelephonica, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId,List<ReleaseDetails> lastTenPredictedValues) {

		int maxSize = defectAcceptanceModelListTelephonica.size();
		int[][] defectAcceptance = new int[maxSize][];
		int [][] lastTenPredictedArray = new int [lastTenPredictedValues.size()][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize][];
		int[][] lclValues = new int[maxSize][];
		int i = 0;
		int j = 0;
		for (ReleaseDetails lastPrediction : lastTenPredictedValues){
			lastTenPredictedArray[j] = new int []{lastPrediction.getReleaseid(),lastPrediction.getPredictedValueInt()};
			j++;
		}
		for(DefectAcceptanceModelTelephonica defect : defectAcceptanceModelListTelephonica)	{
			
			defectAcceptance[i] = new int[]{ defect.getRelease(),defect.getDefectAcceptance()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		uclValues[defectAcceptanceModelListTelephonica.size()] = new int[] {
				defectAcceptanceModelListTelephonica.get(defectAcceptanceModelListTelephonica.size() - 1).getRelease() + 1, ucl[0] };
		lclValues[defectAcceptanceModelListTelephonica.size()] = new int[] {
				defectAcceptanceModelListTelephonica.get(defectAcceptanceModelListTelephonica.size() - 1).getRelease() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = defectAcceptance[defectAcceptance.length-2];
		predictedArrayLinearRegression[1] = new int[]{defectAcceptanceModelListTelephonica.get(defectAcceptanceModelListTelephonica.size()-1).getRelease(),oldList.get(0)};
		System.out.println(defectAcceptance);
		
		
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectAcceptance[defectAcceptance.length-2];
			predictedArraySVRLinearKernel[1] = new int[]{defectAcceptanceModelListTelephonica.get(defectAcceptanceModelListTelephonica.size()-1).getRelease(),oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectAcceptance[defectAcceptance.length-2];
			predictedArraySVRRBFKernel[1] = new int[]{defectAcceptanceModelListTelephonica.get(defectAcceptanceModelListTelephonica.size()-1).getRelease(),oldList.get(2)};
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectAcceptance;
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
			graphData[2] = defectAcceptance;
			//graphData[3] = predictedArrayLinearRegression;
			graphData[3] = lastTenPredictedArray;
			return graphData;
			
		}

	}
	
	
	
	
}
