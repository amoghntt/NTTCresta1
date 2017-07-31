package com.nttdata.web.usecase1D.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
				.getFunctionalDefectCountAndReleaseData(userId);

		int[][][] graphData = createDataSets(resultList, oldList, ucl, lcl,algorithmId);

		return graphData;
	}

	private int[][][] createDataSets(List<FunctionalDefectCountModel> functionalDefectCountModelList,
			List<Integer> oldList, int[] ucl, int[] lcl,int algorithmId) {
		int maxSize = functionalDefectCountModelList.size();
		int[][] functionalDefects = new int[maxSize][];
		int[][] predictedArrayLinearRegression = new int[2][];
		int[][] uclValues = new int[maxSize][];
		int[][] lclValues = new int[maxSize][];
		int i=0;
		for(FunctionalDefectCountModel defect : functionalDefectCountModelList)	{
			
			functionalDefects[i] = new int[]{ defect.getRelease(),defect.getDefectCount()};
			uclValues[i] = new int[]{ defect.getRelease(),ucl[0]};
			lclValues[i] = new int[]{ defect.getRelease(),lcl[0]};
			i++;
		}
		
		predictedArrayLinearRegression[0] = functionalDefects[functionalDefects.length-2];
		predictedArrayLinearRegression[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease(),oldList.get(0)};
		System.out.println(functionalDefects);
		if(algorithmId == 1)
		{
			int[][] predictedArraySVRLinearKernel = new int[2][];
			predictedArraySVRLinearKernel[0] = functionalDefects[functionalDefects.length-2];
			predictedArraySVRLinearKernel[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease(),oldList.get(1)};
			
			int[][] predictedArraySVRRBFKernel = new int[2][];
			predictedArraySVRRBFKernel[0] = functionalDefects[functionalDefects.length-2];
			predictedArraySVRRBFKernel[1] = new int[]{functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1).getRelease(),oldList.get(2)};
			
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
		graphData[3] = predictedArrayLinearRegression;
		return graphData;
		}

		

	}
	

}
