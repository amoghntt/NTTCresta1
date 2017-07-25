
package com.nttdata.web.usecase2.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictedModel;
import com.nttdata.web.service.ConfigService;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase2 {
	@Autowired
	private ConfigService configService;
	
		public FindDefectsServiceUseCase2() {

		}
		
		
		public Map<String, PredictedModel> predictData(int userId, String predictionCode) throws Exception {

			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DD);
			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DL);
			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DR);

			Map<String, PredictedModel> predictResult = new HashMap<String, PredictedModel>();

			try {
				System.out.flush();
				System.out.println("\n");
				System.out.println("\n");

				predictResult = start(userId, predictionCode);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// msgpack-rpc-java does not stop the EventLoop automatically
				EventLoop.defaultEventLoop().shutdown();
				EventLoop.setDefaultEventLoop(null);
			}

			return predictResult;

		}
		
		public Map<String, PredictedModel> start(int userId, String predictionCode) throws Exception {
			RegressionClient clientDD = null;
			RegressionClient clientDL = null;
			RegressionClient clientDR = null;
			Map<String, PredictedModel> predictedResultMap = new HashMap<String, PredictedModel>();

			try {
				clientDD = getJubatusClienttForDefectDensity();
				clientDL = getJubatusClientForDefectLeakage();
				clientDR =getJubatusClientForDefectRejection();
				
				Map<String, List<ModuleDetails>> mapOFTrainData=fetchDataFromDb(userId, predictionCode);
				
				
				for (Map.Entry<String, List<ModuleDetails>> entry : mapOFTrainData.entrySet()) {
					int limitDD = (int) Math.round(mapOFTrainData.get(entry.getKey()).size() * CrestaQueryConstants.N_PERCENT / 100);
					getEstimatedResultForDefectDensity(clientDD,predictedResultMap,mapOFTrainData,entry.getKey(), predictionCode, userId,limitDD);
					int limitDL = (int) Math.round(mapOFTrainData.get(entry.getKey()).size() * CrestaQueryConstants.N_PERCENT / 100);
					getEstimatedResultForDefectLeakage(clientDL,predictedResultMap,mapOFTrainData,entry.getKey(), predictionCode, userId,limitDL);
					int limitDR = (int) Math.round(mapOFTrainData.get(entry.getKey()).size() * CrestaQueryConstants.N_PERCENT / 100);
					getEstimatedResultForDefectRejection(clientDR,predictedResultMap,mapOFTrainData,entry.getKey(), predictionCode, userId,limitDR);
				}

				for (Map.Entry<String, PredictedModel> resultMap : predictedResultMap.entrySet()) {
					PredictedModel predictedModel = new PredictedModel();
					predictedModel = resultMap.getValue();
					System.out.println("Actual values=" + resultMap.getKey() + ": " + predictedModel.getDefectDensity()
							+ " " + predictedModel.getDefectLeakage() + " " + predictedModel.getDefectRejection());
				}

				System.out.println("\n");
				System.out.println("\n");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				clientDD.getClient().close();
				clientDL.getClient().close();
				clientDR.getClient().close();
			}
			return predictedResultMap;
		}

		public RegressionClient getJubatusClienttForDefectDensity() {
			RegressionClient clientDD = null;
			try {
				clientDD = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DD,
						CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return clientDD;
		}

		public RegressionClient getJubatusClientForDefectLeakage() {
			RegressionClient clientDL = null;
			try {
				clientDL = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DL,
						CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return clientDL;
		}

		public RegressionClient getJubatusClientForDefectRejection() {
			RegressionClient clientDR = null;
			try {
				clientDR = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DR,
						CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return clientDR;
		}
		
		
		
		private  Map<String, List<ModuleDetails>> fetchDataFromDb(int userId, String predictionCode) throws IOException {
			Map<String, List<ModuleDetails>> mapOFTrainData = new HashMap<String, List<ModuleDetails>>();
			mapOFTrainData = configService.getModuleWiseData(userId, predictionCode);
			return mapOFTrainData;
		}
		
		private List<ScoredDatum> getTrainingDataForDefectDensity(String moduleName,Map<String, List<ModuleDetails>> mapOFTrainData) {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			List<ModuleDetails> moduleDetailsTrainedList = getTrainData(mapOFTrainData, moduleName);
			for (ModuleDetails module : moduleDetailsTrainedList) {

				Datum datumTrain = new Datum().addNumber("Release", module.getRelease())
						.addNumber("DefectLeakage", module.getDefectLeakage())
						.addNumber("DefectRejection", module.getDefectRejected());
				trainingData.add(new ScoredDatum(module.getDefectDensity(), datumTrain));
			}
			return trainingData;
		}
		
		private List<ScoredDatum> getTrainingDataForDefectLeakage(String moduleName,Map<String, List<ModuleDetails>> mapOFTrainData) {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			List<ModuleDetails> moduleDetailsTrainedList = getTrainData(mapOFTrainData, moduleName);
			for (ModuleDetails module : moduleDetailsTrainedList) {

				Datum datumTrain = new Datum().addNumber("Release", module.getRelease())
						.addNumber("DefectDensity", module.getDefectDensity())
						.addNumber("DefectRejection", module.getDefectRejected());

				trainingData.add(new ScoredDatum(module.getDefectLeakage(), datumTrain));
			}
			return trainingData;
		}
		
		private List<ScoredDatum> getTrainingDataForDefectRejection(String moduleName,Map<String, List<ModuleDetails>> mapOFTrainData) {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			List<ModuleDetails> moduleDetailsTrainedList = getTrainData(mapOFTrainData, moduleName);
			for (ModuleDetails module : moduleDetailsTrainedList) {

				Datum datumTrain = new Datum().addNumber("Release", module.getRelease())
						.addNumber("DefectLeakage", module.getDefectLeakage())
						.addNumber("DefectDensity", module.getDefectDensity());

				trainingData.add(new ScoredDatum(module.getDefectRejected(), datumTrain));
			}
			return trainingData;
		}

		private Map<String, List<Datum>> getTestDataForDefectDensity(String moduleName, int userId, String predictionCode,int limit) {
			List<ModuleDetails> moduleDetailsList = getInputModel(userId, predictionCode, moduleName, limit);
			List<Datum> datumList = new ArrayList<Datum>();
			Datum datumInput = null;
			Map<String, List<Datum>> inputDataMap = new HashMap<String, List<Datum>>();
			for (ModuleDetails module : moduleDetailsList) {
				datumInput = new Datum().addNumber("Release", module.getRelease())
						.addNumber("DefectLeakage", module.getDefectLeakage())
						.addNumber("DefectRejection", module.getDefectRejected());

				datumList.add(datumInput);
				System.out.println("Details:" + moduleName + "=" + module.getRelease() + "=" + module.getDefectDensity()
						+ "=" + module.getDefectRejected());
				inputDataMap.put(moduleName, datumList);

			}
			
			return inputDataMap;

		}
		
	private Map<String, List<Datum>> getTestDataForDefectLeakage(String moduleName, int userId, String predictionCode,int limit) {
		List<ModuleDetails> moduleDetailsList = getInputModel(userId, predictionCode, moduleName, limit);
		List<Datum> datumList = new ArrayList<Datum>();
		Datum datumInput = null;
		Map<String, List<Datum>> inputDataMap = new HashMap<String, List<Datum>>();
		for (ModuleDetails module : moduleDetailsList) {
			datumInput = new Datum().addNumber("Release", module.getRelease())
					.addNumber("DefectDensity", module.getDefectDensity())
					.addNumber("DefectRejection", module.getDefectRejected());

			System.out.println("Details:" + moduleName + "=" + module.getRelease() + "=" + module.getDefectDensity()
					+ "=" + module.getDefectRejected());

			datumList.add(datumInput);
			inputDataMap.put(moduleName, datumList);

		}
		
		return inputDataMap;

	}
	
	
	private Map<String, List<Datum>> getTestDataForDefectRejection(String moduleName, int userId, String predictionCode,int limit) {
		List<ModuleDetails> moduleDetailsList = getInputModel(userId, predictionCode, moduleName, limit);
		List<Datum> datumList = new ArrayList<Datum>();
		Datum datumInput = null;
		Map<String, List<Datum>> inputDataMap = new HashMap<String, List<Datum>>();
		for (ModuleDetails module : moduleDetailsList) {
			datumInput = new Datum().addNumber("Release", module.getRelease())
					.addNumber("DefectDensity", module.getDefectDensity())
					.addNumber("DefectRejection", module.getDefectLeakage());

			System.out.println("Details:" + moduleName + "=" + module.getRelease() + "=" + module.getDefectDensity()
					+ "=" + module.getDefectRejected());

			datumList.add(datumInput);
			inputDataMap.put(moduleName, datumList);

		}
		
		return inputDataMap;

	}
	
	
	private List<ModuleDetails> getInputModel(int userId, String predictionCode, String moduleName, int limit) {
		return configService.getAvgData(userId, predictionCode, moduleName, limit);
	}
	
	public List<ModuleDetails> getTrainData(Map<String, List<ModuleDetails>> mapOFTrainData, String moduleName) {

		List<ModuleDetails> moduleDetailsList = new ArrayList<ModuleDetails>();

		for (String key : mapOFTrainData.keySet()) {
			if (key.equals(moduleName)) {
				List<ModuleDetails> moduleDetails = mapOFTrainData.get(key);

				if (moduleDetails != null) {

					moduleDetailsList.addAll(moduleDetails);
				}
			}
		}
		return moduleDetailsList;

	}

	private void getEstimatedResultForDefectDensity(RegressionClient clientDD,Map<String, PredictedModel> predictedResultMap,Map<String, List<ModuleDetails>> mapOFTrainData, String moduleName,
			String predictionCode, int userId,int limit) {
		
		List<ScoredDatum>trainDataForDefectDensity = getTrainingDataForDefectDensity(moduleName,mapOFTrainData);
		clientDD.train(trainDataForDefectDensity);
		clientDD.save(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_DENSITY);
		clientDD.load(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_DENSITY);
		
		
		Map<String, List<Datum>> testDataForDefectDensity = getTestDataForDefectDensity(moduleName, userId, predictionCode,limit);
		for (Map.Entry<String, List<Datum>> inputDatum : testDataForDefectDensity.entrySet()) {
			List<Float> resultList = clientDD.estimate(inputDatum.getValue());
			for (Float value : resultList) {
				System.out.println("values=" + value);
			}
			double estimatedValue = resultList.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}

			if (predictedResultMap.containsKey(inputDatum.getKey())) {

				PredictedModel predictedModel = predictedResultMap.get(inputDatum.getKey());
				predictedModel.setDefectDensity((int) Math.round(estimatedValue));

			} else {
				PredictedModel predictedModel = new PredictedModel();
				predictedModel.setDefectDensity((int) Math.round(estimatedValue));

				predictedResultMap.put(inputDatum.getKey(), predictedModel);
			}

		}

	}

	private void getEstimatedResultForDefectLeakage(RegressionClient clientDL,Map<String, PredictedModel> predictedResultMap,Map<String, List<ModuleDetails>> mapOFTrainData, String moduleName,
			String predictionCode, int userId,int limit) {
		List<ScoredDatum>trainDataForDefectLeakage = getTrainingDataForDefectLeakage(moduleName,mapOFTrainData);
		clientDL.train(trainDataForDefectLeakage);
		clientDL.save(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_LEAKAGE);
		clientDL.load(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_LEAKAGE);
		Map<String, List<Datum>> testDataForDefectLeakage=getTestDataForDefectLeakage(moduleName, userId, predictionCode,limit);
		for (Map.Entry<String, List<Datum>> inputDatumForDefectLeakage : testDataForDefectLeakage.entrySet()) {
			System.out.println(moduleName + "=" + inputDatumForDefectLeakage.getValue());
			List<Float> resultListForDefectLeakage = clientDL.estimate(inputDatumForDefectLeakage.getValue());
			for (Float value : resultListForDefectLeakage) {
				System.out.println("values=" + value);
			}
			double estimatedValue = resultListForDefectLeakage.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}

			if (predictedResultMap.containsKey(inputDatumForDefectLeakage.getKey())) {

				PredictedModel predictedModel = predictedResultMap.get(inputDatumForDefectLeakage.getKey());
				predictedModel.setDefectLeakage((int) Math.round(estimatedValue));

			} else {
				PredictedModel predictedModel = new PredictedModel();
				predictedModel.setDefectLeakage((int) Math.round(estimatedValue));

				predictedResultMap.put(inputDatumForDefectLeakage.getKey(), predictedModel);
			}

		}
	}

	private void getEstimatedResultForDefectRejection(RegressionClient clientDR,Map<String, PredictedModel> predictedResultMap,Map<String, List<ModuleDetails>> mapOFTrainData, String moduleName,
			String predictionCode, int userId,int limit) {
		
		List<ScoredDatum>trainDataForDefectRejection = getTrainingDataForDefectRejection(moduleName,mapOFTrainData);
		clientDR.train(trainDataForDefectRejection);
		clientDR.save(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_REJECTION);
		clientDR.load(CrestaConstants.MODEL_REGRESSION_UC2_DEFECT_REJECTION);
		Map<String, List<Datum>> testDataForDefetRejection=getTestDataForDefectRejection(moduleName, userId, predictionCode,limit);
		
		for (Map.Entry<String, List<Datum>> inputDatumForDefectRejection : testDataForDefetRejection.entrySet()) {
			List<Float> resultListForDefectRejection = clientDR.estimate(inputDatumForDefectRejection.getValue());
			for (Float value : resultListForDefectRejection) {
				System.out.println("values=" + value);
			}
			double estimatedValue = resultListForDefectRejection.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}
			if (predictedResultMap.containsKey(inputDatumForDefectRejection.getKey())) {

				PredictedModel predictedModel = predictedResultMap.get(inputDatumForDefectRejection.getKey());

				predictedModel.setDefectRejection((int) Math.round(estimatedValue));

			} else {
				PredictedModel predictedModel = new PredictedModel();

				predictedModel.setDefectRejection((int) Math.round(estimatedValue));
				predictedResultMap.put(inputDatumForDefectRejection.getKey(), predictedModel);
			}
		}
	}

}
