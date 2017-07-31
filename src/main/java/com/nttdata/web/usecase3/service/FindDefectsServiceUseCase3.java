package com.nttdata.web.usecase3.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

//import com.jubatus.usingdefectdensity.GetData1;
@Service
public class FindDefectsServiceUseCase3 {

	@Autowired
	private ConfigService configService;
		public FindDefectsServiceUseCase3() {

		}

		public int predictData(int userId, String predictionCode) throws Exception {
			int predictResult = 0;
			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DL);
			try {
				System.out.flush();
				System.out.println("\n");
				System.out.println("\n");
				predictResult = start(userId, predictionCode);
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
				List<DefectLeakageModel> defectLeakageModelList = fetchDataFromDb(userId, predictionCode);
				int limit = (int) Math.round(defectLeakageModelList.size() * CrestaQueryConstants.N_PERCENT / 100);
				List<ScoredDatum> trainingData = getTrainingData(defectLeakageModelList);
				client.train(trainingData);
				client.save(CrestaConstants.MODEL_REGRESSION_UC3_DEFECT_LEAKAGE);
				client.load(CrestaConstants.MODEL_REGRESSION_UC3_DEFECT_LEAKAGE);
				List<Datum> testData = getTestData(defectLeakageModelList,userId, predictionCode,limit);
				List<Float> resultList = client.estimate(testData);
				double estimatedValue = resultList.get(0).doubleValue();
				if (estimatedValue < 0.0) {
					estimatedValue = 0.0;
				}
				predictResult = (int) Math.round(estimatedValue);
				System.out.println("Defect_Leakage: " + predictResult);
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
				client = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DL,
						CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return client;
		}
		private List<DefectLeakageModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
			List<DefectLeakageModel> defectLeakageModelList = configService.getDefectLeakageData(userId, predictionCode);
			return defectLeakageModelList;
		}
		private DefectLeakageModel getInputModel(int userId, String predictionCode, int limit) {
			return configService.getAvgDataForUseCase3(userId, predictionCode, limit).get(0);
		}
		

		private List<ScoredDatum> getTrainingData(List<DefectLeakageModel> defectLeakageModelList) throws IOException {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			for (int counter = 0; counter < defectLeakageModelList.size() - 1; counter++) {
			DefectLeakageModel defectLeakageModel = defectLeakageModelList.get(counter);
			Datum datumTrain = new Datum().addNumber("Release", defectLeakageModel.getRelease())
					.addNumber("DefectDensity", defectLeakageModel.getDefectDensity())
					.addNumber("DefectRejection", defectLeakageModel.getDefectRejected());
				trainingData.add(new ScoredDatum(defectLeakageModel.getDefectLeakage(), datumTrain));
			}

			return trainingData;
		}

		private List<Datum> getTestData(List<DefectLeakageModel> defectLeakageModelList,int userId, String predictionCode,int limit) throws IOException {
			List<Datum> testData;
			DefectLeakageModel defectLeakageModel = getInputModel(userId, predictionCode, limit);
			Datum datumInput = new Datum().addNumber("Release", defectLeakageModel.getRelease())
					.addNumber("DefectDensity", defectLeakageModel.getDefectDensity())
					.addNumber("DefectRejection", defectLeakageModel.getDefectRejected());
			Datum[] testDataArray = { datumInput };
			testData = Arrays.asList(testDataArray);
			return testData;
		}

}
