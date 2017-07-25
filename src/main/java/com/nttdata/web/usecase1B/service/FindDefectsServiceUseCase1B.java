package com.nttdata.web.usecase1B.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase1B {

	@Autowired
	private ConfigService configService;

		public FindDefectsServiceUseCase1B() {

		}

		public int predictData(int userId, String predictionCode) throws Exception {
			int predictResult = 0;
			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DF);
			try {
				System.out.flush();
				System.out.println("\n");
				System.out.println("\n");
				predictResult = startJubatusTelephonica(userId, predictionCode);
				// JubatusUtils.stopJubatus(CrestaQueryConstants.PRED_PORT);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// msgpack-rpc-java does not stop the EventLoop automatically
				EventLoop.defaultEventLoop().shutdown();
				EventLoop.setDefaultEventLoop(null);
			}

			return predictResult;
		}

		public int start(int userId, String predictionCode) throws Exception {
			RegressionClient client = null;
			int predictResult = 0;

			try {
				client = getJubatusClient();
				List<DefectDeferralModel> defectDeferralModelList = fetchDataFromDb(userId, predictionCode);
				List<ScoredDatum> trainingData = getTrainingData(defectDeferralModelList);
				client.train(trainingData);
				client.save(CrestaConstants.MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE);
				client.load(CrestaConstants.MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE);
				List<Datum> testData = getTestData(defectDeferralModelList);
				List<Float> resultList = client.estimate(testData);
				double estimatedValue = resultList.get(0).doubleValue();
				if (estimatedValue < 0.0) {
					estimatedValue = 0.0;
				}
				predictResult = (int) Math.round(estimatedValue);
				System.out.println("Defect_Deferral: " + predictResult);
				System.out.println("\n");
				System.out.println("\n");
			} finally {
				if (null != client) {
					client.getClient().close();
				}
			}
			return predictResult;
		}
		
		public int startJubatusTelephonica(int userId, String predictionCode) throws Exception {
			RegressionClient client = null;
			int predictResult = 0;

			try {
				client = getJubatusClient();
				List<DefectDeferralModelTelephonica> defectDeferralModelList = fetchDataFromDbTelephonica(userId, predictionCode);
				List<ScoredDatum> trainingData = getTrainingDataTelephonica(defectDeferralModelList);
				client.train(trainingData);
				client.save(CrestaConstants.MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE);
				client.load(CrestaConstants.MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE);
				List<Datum> testData = getTestDataTelephonica(defectDeferralModelList);
				List<Float> resultList = client.estimate(testData);
				double estimatedValue = resultList.get(0).doubleValue();
				if (estimatedValue < 0.0) {
					estimatedValue = 0.0;
				}
				predictResult = (int) Math.round(estimatedValue);
				System.out.println("Defect_Deferral: " + predictResult);
				System.out.println("\n");
				System.out.println("\n");
			} finally {
				if (null != client) {
					client.getClient().close();
				}
			}
			return predictResult;
		}


		private RegressionClient getJubatusClient() {
			RegressionClient client = null;
			try {
				client = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DF,
						CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return client;
		}
		private List<DefectDeferralModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
			List<DefectDeferralModel> defectDeferralModelList = configService.getDefectDeferralData(userId, predictionCode);
			return defectDeferralModelList;
		}

		private List<DefectDeferralModelTelephonica> fetchDataFromDbTelephonica(int userId, String predictionCode) throws IOException {
			List<DefectDeferralModelTelephonica> defectDeferralModelList = configService.getDefectDeferralTelephonicaData(userId, predictionCode);
			return defectDeferralModelList;
		}
		private List<ScoredDatum> getTrainingData(List<DefectDeferralModel> defectDeferralModelList) throws IOException {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			for (int counter = 0; counter < defectDeferralModelList.size() - 1; counter++) {
				DefectDeferralModel defectDeferralModel = defectDeferralModelList.get(counter);
				Datum datumTrain = new Datum().addNumber("DefectSeverity", defectDeferralModel.getDefectSeverity())
						.addNumber("DefectPriority", defectDeferralModel.getDefectPriority())
						.addNumber("EffortToFixDefect", defectDeferralModel.getEffortToFixDefect())
						.addNumber("CostToFixDefect", defectDeferralModel.getCostToFixDefect())
						.addNumber("ImpactOfDefect", defectDeferralModel.getImpactOfDefectFix())
						// .addNumber("Feasibility",
						// defectDeferralModel.isFeasibilityWithinMilestone())
						.addNumber("AvailabilityOfBudget", defectDeferralModel.getAvailabilityOfBudget())
						.addNumber("ComplexityOfDefectFix", defectDeferralModel.getComplexityofDefectFix());

				trainingData.add(new ScoredDatum(defectDeferralModel.getDefectDeferralCount(), datumTrain));
			}

			return trainingData;
		}
		
		private List<ScoredDatum> getTrainingDataTelephonica(List<DefectDeferralModelTelephonica> defectDeferralModelList) throws IOException {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			for (int counter = 0; counter < defectDeferralModelList.size(); counter++) {
				DefectDeferralModelTelephonica defectDeferralModel = defectDeferralModelList.get(counter);
				Datum datumTrain = new Datum().addNumber("DefectSeverity", defectDeferralModel.getBg_Severity())
						.addNumber("DefectPriority", defectDeferralModel.getBg_Priority())
						.addNumber("EffortToFixDefect", defectDeferralModel.getEffortToFixDefect())
						.addNumber("CostToFixDefect", defectDeferralModel.getCostToFixDefect())
						.addNumber("ImpactOfDefect", defectDeferralModel.getImpactOfDefectFix())
						// .addNumber("Feasibility",
						// defectDeferralModel.isFeasibilityWithinMilestone())
						.addNumber("AvailabilityOfBudget", defectDeferralModel.getAvailabilityOfBudget())
						.addNumber("ComplexityOfDefectFix", defectDeferralModel.getComplexityofDefectFix());

				trainingData.add(new ScoredDatum(defectDeferralModel.getDefectDeferralCount(), datumTrain));
			}

			return trainingData;
		}

		private List<Datum> getTestData(List<DefectDeferralModel> defectDeferralModelList) throws IOException {
			List<Datum> testData;
			DefectDeferralModel defectDeferralModelNew = defectDeferralModelList.get(defectDeferralModelList.size()-1);
			Datum datumInput = new Datum().addNumber("DefectSeverity", defectDeferralModelNew.getDefectSeverity())
					.addNumber("DefectPriority", defectDeferralModelNew.getDefectPriority())
					.addNumber("EffortToFixDefect", defectDeferralModelNew.getEffortToFixDefect())
					.addNumber("CostToFixDefect", defectDeferralModelNew.getCostToFixDefect())
					.addNumber("ImpactOfDefect", defectDeferralModelNew.getImpactOfDefectFix())
					// .addNumber("Feasibility",
					// defectDeferralModel.isFeasibilityWithinMilestone())
					.addNumber("AvailabilityOfBudget", defectDeferralModelNew.getAvailabilityOfBudget())
					.addNumber("ComplexityOfDefectFix", defectDeferralModelNew.getComplexityofDefectFix());
			Datum[] testDataArray = { datumInput };
			testData = Arrays.asList(testDataArray);
			return testData;
		}
		
		private List<Datum> getTestDataTelephonica(List<DefectDeferralModelTelephonica> defectDeferralModelList) throws IOException {
			List<Datum> testData;
			DefectDeferralModelTelephonica defectDeferralModelNew = defectDeferralModelList.get(defectDeferralModelList.size()-1);
			Datum datumInput = new Datum().addNumber("DefectSeverity", defectDeferralModelNew.getBg_Severity())
					.addNumber("DefectPriority", defectDeferralModelNew.getBg_Priority())
					.addNumber("EffortToFixDefect", defectDeferralModelNew.getEffortToFixDefect())
					.addNumber("CostToFixDefect", defectDeferralModelNew.getCostToFixDefect())
					.addNumber("ImpactOfDefect", defectDeferralModelNew.getImpactOfDefectFix())
					// .addNumber("Feasibility",
					// defectDeferralModel.isFeasibilityWithinMilestone())
					.addNumber("AvailabilityOfBudget", defectDeferralModelNew.getAvailabilityOfBudget())
					.addNumber("ComplexityOfDefectFix", defectDeferralModelNew.getComplexityofDefectFix());
			Datum[] testDataArray = { datumInput };
			testData = Arrays.asList(testDataArray);
			return testData;
		}
}
