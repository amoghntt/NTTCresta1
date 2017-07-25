package com.nttdata.web.usecase1C.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase1C {

	@Autowired
	private ConfigService configService;
	public FindDefectsServiceUseCase1C() {

	}

	public int predictData(int userId, String predictionCode) throws Exception {
		int predictResult = 0;
		JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
				"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_AD);
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
			List<DefectCountModel> defectCountModelList = fetchDataFromDb(userId, predictionCode);
			List<ScoredDatum> trainingData = getTrainingData(defectCountModelList);
			client.train(trainingData);
			client.save(CrestaConstants.MODEL_REGRESSION_UC1C_ALL_DEFECTS);
			client.load(CrestaConstants.MODEL_REGRESSION_UC1C_ALL_DEFECTS);
			List<Datum> testData = getTestData(defectCountModelList);
			List<Float> resultList = client.estimate(testData);
			double estimatedValue = resultList.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}
			predictResult = (int) Math.round(estimatedValue);
			System.out.println("Defect_Count: " + predictResult);
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
			client = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_AD,
					CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	private List<DefectCountModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
		List<DefectCountModel> defectCountModelList = configService.getDefectCountData(userId);
		return defectCountModelList;
	}

	private List<ScoredDatum> getTrainingData(List<DefectCountModel> defectCountModelList) throws IOException {
		List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
		for (int counter = 0; counter < defectCountModelList.size() - 1; counter++) {
			DefectCountModel defectCountModel = defectCountModelList.get(counter);
			Datum datumTrain = new Datum().addNumber("Kloc", defectCountModel.getkLoc())
					.addNumber("TestCaseCount", defectCountModel.getTestCaseCount())
					.addNumber("ApplicationComplexity", defectCountModel.getApplicationComplexity())
					.addNumber("DomainKnowledge", defectCountModel.getDomainKnowledge())
					.addNumber("TechnicalSkills", defectCountModel.getTechnicalSkills())
					.addNumber("CodeReviewComments", defectCountModel.getCodeReviewComments())
					.addNumber("DesignReviewComments", defectCountModel.getDesignReviewComments())
					.addNumber("RequirementsQueryCount", defectCountModel.getRequirementsQueryCount());


			trainingData.add(new ScoredDatum(defectCountModel.getDefectDensity(), datumTrain));
		}

		return trainingData;
	}

	private List<Datum> getTestData(List<DefectCountModel> defectCountModelList) throws IOException {
		List<Datum> testData;
		DefectCountModel defectCountModel = defectCountModelList.get(defectCountModelList.size()-1);
		// count=21
		Datum datumInput = new Datum().addNumber("Kloc", defectCountModel.getkLoc())
				.addNumber("TestCaseCount", defectCountModel.getTestCaseCount())
				.addNumber("ApplicationComplexity", defectCountModel.getApplicationComplexity())
				.addNumber("DomainKnowledge", defectCountModel.getDomainKnowledge())
				.addNumber("TechnicalSkills", defectCountModel.getTechnicalSkills())
				.addNumber("CodeReviewComments", defectCountModel.getCodeReviewComments())
				.addNumber("RequirementsQueryCount", defectCountModel.getRequirementsQueryCount())
				.addNumber("DesignReviewComments", defectCountModel.getDesignReviewComments());
		Datum[] testDataArray = { datumInput };
		testData = Arrays.asList(testDataArray);
		return testData;
	}
	
}