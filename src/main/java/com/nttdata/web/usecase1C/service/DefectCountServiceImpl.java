package com.nttdata.web.usecase1C.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.nttdata.web.usecase1C.dao.DefectCountDAO;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.service.ETL;


@Service
public class DefectCountServiceImpl implements DefectCountService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectCountDAO newDefectDensityDAO;

	@Override
	public int[][][] processTask(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, int[] ucl, int[] lcl, int algorithmId) {
		List<Integer> oldList = new ArrayList<Integer>();
				oldList = etlBean.interactETL(useCaseType, predictionId, metricsId, userId, redmineProjectId,algorithmId);
		List<DefectCountModel> resultList = newDefectDensityDAO.getDefectCountData(Integer.parseInt(userId));

		int[][][] graphData = createDataSets(resultList, oldList, ucl, lcl,algorithmId);

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

}
