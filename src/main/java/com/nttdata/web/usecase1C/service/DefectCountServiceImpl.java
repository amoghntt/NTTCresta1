package com.nttdata.web.usecase1C.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase1C.dao.DefectCountDAO;
import com.nttdata.web.usecase1C.model.DefectCountModel;


@Service
public class DefectCountServiceImpl implements DefectCountService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectCountDAO defectCountDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl, int algorithmId) {
		List<Integer> oldList = new ArrayList<Integer>();
				oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		List<DefectCountModel> resultList = defectCountDAO.getDefectCountData(Integer.parseInt(userId));
		
		List<ReleaseDetails> lastTenPredictedValues =defectCountDAO.getLastTenPredictions(algorithmId);
		
		int[][][] graphData = createDataSetsTelephonica(resultList, oldList, ucl, lcl,algorithmId,lastTenPredictedValues);

		return graphData;
	}

	private int[][][] createDataSets(List<DefectCountModel> defectCountModelList, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId) {

		int maxSize = defectCountModelList.size();
		int[][] defectCount = new int[maxSize][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize][];
		int[][] lclValues = new int[maxSize][];
		int i=0;
		for(DefectCountModel defect : defectCountModelList)	{
			
			defectCount[i] = new int[]{ defect.getRelease(),defect.getDefectDensity()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		
		predictedArrayLinearRegression[0] = defectCount[defectCount.length-2];
		predictedArrayLinearRegression[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease(),oldList.get(0)};
		System.out.println(defectCount);
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectCount[defectCount.length-2];
			predictedArraySVRLinearKernel[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease(),oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectCount[defectCount.length-2];
			predictedArraySVRRBFKernel[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease(),oldList.get(2)};
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectCount;
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
			graphData[2] = defectCount;
			graphData[3] = predictedArrayLinearRegression;
			
			return graphData;
			
		}
		
	}
	
	private int[][][] createDataSetsTelephonica(List<DefectCountModel> defectCountModelList, List<Integer> oldList, int[] ucl,
			int[] lcl,int algorithmId,List<ReleaseDetails> lastTenPredictedValues) {

		int maxSize = defectCountModelList.size();
		int[][] defectCount = new int[maxSize][];
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
		for(DefectCountModel defect : defectCountModelList)	{
			
			defectCount[i] = new int[]{ defect.getRelease(),defect.getDefectDensity()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		uclValues[defectCountModelList.size()] = new int[] {
				defectCountModelList.get(defectCountModelList.size() - 1).getRelease() + 1, ucl[0] };
		lclValues[defectCountModelList.size()] = new int[] {
				defectCountModelList.get(defectCountModelList.size() - 1).getRelease() + 1, lcl[0] };
		predictedArrayLinearRegression[0] = defectCount[defectCount.length-2];
		predictedArrayLinearRegression[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease(),oldList.get(0)};
		System.out.println(defectCount);
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = defectCount[defectCount.length-2];
			predictedArraySVRLinearKernel[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease() + 1,oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = defectCount[defectCount.length-2];
			predictedArraySVRRBFKernel[1] = new int[]{defectCountModelList.get(defectCountModelList.size()-1).getRelease() + 1,oldList.get(2)};
			
			int graphData[][][] = new int[6][][];
			graphData[0] = lclValues;
			graphData[1] = uclValues;
			graphData[2] = defectCount;
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
			graphData[2] = defectCount;
			//graphData[3] = predictedArrayLinearRegression;
			graphData[3] = lastTenPredictedArray;
			return graphData;
			
		}
		
	}

}
