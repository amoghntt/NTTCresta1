package com.nttdata.web.usecase2.service;

import com.nttdata.web.model.PredictedModel;
import com.nttdata.web.service.ETL;
import com.nttdata.web.usecase2.dao.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;

@Service
public class DefectiveModulesServiceImpl implements DefectiveModulesService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private DefectiveModulesDAO defectiveModuleDAO;

	@Override
	public List<List<ModuleDetails>> processTask(String predictionCode, String metricsId, String userId,
			int redmineProjectId, List<MetricsBean> metricsList) {

		Map<String, PredictedModel> moduleWiseData = new HashMap<String, PredictedModel>();
		moduleWiseData = etlBean.interactETLForUseCase2("predictUseCase2", predictionCode, metricsId, userId,
				redmineProjectId);
		List<List<ModuleDetails>> moduleDisplayList = new ArrayList<List<ModuleDetails>>();

		moduleDisplayList = getModuleDisplayList(moduleWiseData, metricsList);

		return moduleDisplayList;
	}

	@Override
	public List<Double> predict(String predictionCode, String metricsId, String userId, int redmineProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAxesLabel() {

		List<String> axesLabel = new ArrayList<>();
		axesLabel.add("Release");
		axesLabel.add("Defect Density");
		axesLabel.add("Defect Leakage");
		axesLabel.add("Defect Rejection");
		return axesLabel;
	}

	private int[][][] createDataSets(String defectMetrics, int[] ucl, int[] lcl, int predictedValues,
			List<Integer> defectParameterList, List<Integer> releaseCountList) {
		
		
		int maxSize = defectParameterList.size();
		int[][] functionalDefects = new int[maxSize][];
		int[][] predictedArray = new int[2][];
		int[][] uclValues = new int[maxSize+1][];
		int[][] lclValues = new int[maxSize+1][];
		System.out.println( releaseCountList.get(0));
		for(int i=0;i<defectParameterList.size();i++)	{
			
			lclValues[i] = new int []{releaseCountList.get(i), lcl[0]};
			uclValues[i] = new int []{releaseCountList.get(i), ucl[0]};
			functionalDefects[i] =new int []{releaseCountList.get(i),defectParameterList.get(i)};
			
		}
		uclValues[defectParameterList.size()]=new int []{releaseCountList.get(releaseCountList.size()-1)+1,ucl[0]};
		lclValues[defectParameterList.size()]=new int []{releaseCountList.get(releaseCountList.size()-1)+1,lcl[0]};
	
		int[] predictedToPlot = new int[2];
		predictedToPlot = functionalDefects[functionalDefects.length-1];
		
		predictedArray[0] = predictedToPlot;
		predictedArray[1]= new int[]{ releaseCountList.get(releaseCountList.size()-1)+1, predictedValues};
		
		System.out.println(functionalDefects);
		int graphData[][][] = new int[4][][];
		graphData[0] = lclValues;
		graphData[1] = uclValues;
		graphData[2] = functionalDefects;
		graphData[3] = predictedArray;
		

		return graphData;

	}

	@Override
	public List<List<ModuleDetails>> getModuleDisplayList(Map<String, PredictedModel> moduleWiseData,
			List<MetricsBean> metricsList) {
		List<ModuleDetails> redList = new ArrayList<ModuleDetails>();
		List<ModuleDetails> greenList = new ArrayList<ModuleDetails>();
		List<ModuleDetails> amberList = new ArrayList<ModuleDetails>();
		for (Entry<String, PredictedModel> moduleWiseMapEntry : moduleWiseData.entrySet()) {
			PredictedModel predictedModule = moduleWiseMapEntry.getValue();
			ModuleDetails moduleDetails = new ModuleDetails();
			moduleDetails.setModuleName(moduleWiseMapEntry.getKey());
			moduleDetails.setDefectDensity(predictedModule.getDefectDensity());
			moduleDetails.setDefectLeakage(predictedModule.getDefectLeakage());
			moduleDetails.setDefectRejected(predictedModule.getDefectRejection());
			int risk = getCategory(moduleWiseMapEntry.getValue(), metricsList);
			if (risk == 1) {
				greenList.add(moduleDetails);
			} else if (risk == 2) {
				amberList.add(moduleDetails);
			} else {
				redList.add(moduleDetails);
			}

		}

		List<List<ModuleDetails>> moduleDisplayList = new ArrayList<List<ModuleDetails>>();
		int maxCount = getMaxSize(redList, greenList);
		if (amberList.size() > maxCount) {
			maxCount = amberList.size();
		}
		for (int count = 0; count < maxCount; count++) {
			List<ModuleDetails> moduleDetailsList = new ArrayList<ModuleDetails>();
			moduleDetailsList.add(getRequiredModule(redList, count));
			moduleDetailsList.add(getRequiredModule(amberList, count));
			moduleDetailsList.add(getRequiredModule(greenList, count));
			moduleDisplayList.add(moduleDetailsList);
		}
		return moduleDisplayList;
	}

	private int getMaxSize(List<ModuleDetails> list1, List<ModuleDetails> list2) {
		if (list1.size() > list2.size()) {
			return list1.size();

		} else {
			return list2.size();
		}

	}

	private ModuleDetails getRequiredModule(List<ModuleDetails> moduleDetailsList, int indexNo) {
		if (indexNo < moduleDetailsList.size()) {
			return moduleDetailsList.get(indexNo);
		} else
			return null;
	}

	private int getCategory(PredictedModel moduleWisePredictedValueOfMetrics, List<MetricsBean> metricsList) {

		double weightedPredictedValue = 0;
		double weightedFirstLcl = 0;
		double weightedFirstUcl = 0;
		double weightedSecondUcl = 0;
		double weightedSecondLcl = 0;
		double weightedThirdUcl = 0;
		double weightedThirdLcl = 0;
		int[] ucl;
		int[] lcl;

		for (MetricsBean metricsBean : metricsList) {

			System.out.println(metricsBean.getMetricsId());
			System.out.println(metricsBean.getLcl());
			System.out.println(metricsBean.getUcl());
			System.out.println(metricsBean.getWeightage());
			ucl = metricsBean.getUcl();
			lcl = metricsBean.getLcl();
			weightedFirstLcl = weightedFirstLcl + lcl[0] * metricsBean.getWeightage();
			weightedFirstUcl = weightedFirstUcl + ucl[0] * metricsBean.getWeightage();

			weightedSecondLcl = weightedSecondLcl + lcl[1] * metricsBean.getWeightage();
			weightedSecondUcl = weightedSecondUcl + ucl[1] * metricsBean.getWeightage();
		}
		weightedFirstLcl = weightedFirstLcl / (100 * metricsList.size());
		weightedSecondLcl = weightedSecondLcl / (100 * metricsList.size());
		weightedThirdLcl = weightedThirdLcl / (100 * metricsList.size());
		weightedFirstUcl = weightedFirstUcl / (100 * metricsList.size());
		weightedSecondUcl = weightedSecondUcl / (100 * metricsList.size());
		weightedThirdUcl = weightedThirdUcl / (100 * metricsList.size());

		weightedPredictedValue = (moduleWisePredictedValueOfMetrics.getDefectDensity()
				* (metricsList.get(0).getWeightage())
				+ moduleWisePredictedValueOfMetrics.getDefectLeakage() * (metricsList.get(1).getWeightage())
				+ moduleWisePredictedValueOfMetrics.getDefectRejection() * (metricsList.get(2).getWeightage()));
		weightedPredictedValue = weightedPredictedValue / 300;

		if (weightedPredictedValue >= weightedFirstLcl && weightedPredictedValue < weightedFirstUcl) {
			return 1;
		} else if (weightedPredictedValue >= weightedSecondLcl && weightedPredictedValue < weightedSecondUcl) {
			return 2;
		} else {
			return 3;
		}

	}

	@Override
	public List<int[][][]> getModuleDetails(String moduleName, String predictionCode, int userId,
			List<MetricsBean> metricsBeanList, int[] predictedValue) {
		List<int[][][]> graphBeanList = new ArrayList<int[][][]>();

		Map<String, List<Integer>> moduleDetailsResult = defectiveModuleDAO.getModuleData(moduleName, predictionCode);

		graphBeanList.add(createDataSets("Defect Density", metricsBeanList.get(0).getUcl(),
				metricsBeanList.get(0).getLcl(), predictedValue[0], moduleDetailsResult.get("DefectDensity"),
				moduleDetailsResult.get("ReleaseVersion")));
		graphBeanList.add(createDataSets("Defect Leakage", metricsBeanList.get(1).getUcl(),
				metricsBeanList.get(1).getLcl(), predictedValue[1], moduleDetailsResult.get("DefectLeakage"),
				moduleDetailsResult.get("ReleaseVersion")));
		graphBeanList.add(createDataSets("Defect Rejection", metricsBeanList.get(2).getUcl(),
				metricsBeanList.get(2).getLcl(), predictedValue[2], moduleDetailsResult.get("DefectRejection"),
				moduleDetailsResult.get("ReleaseVersion")));

		return graphBeanList;

	}

}
