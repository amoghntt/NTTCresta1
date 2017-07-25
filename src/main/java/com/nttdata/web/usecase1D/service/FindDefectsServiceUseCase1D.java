package com.nttdata.web.usecase1D.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase1D {


	@Autowired
	private ConfigService configService;
	
	public FindDefectsServiceUseCase1D() {

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
			List<FunctionalDefectCountModel> functionalDefectCountModelList = fetchDataFromDb(userId, predictionCode);
			List<ScoredDatum> trainingData = getTrainingData(functionalDefectCountModelList);
			client.train(trainingData);
			client.save(CrestaConstants.MODEL_REGRESSION_UC1D_FUNCTIONAL_DEFECT_COUNT);
			client.load(CrestaConstants.MODEL_REGRESSION_UC1D_FUNCTIONAL_DEFECT_COUNT);
			List<Datum> testData = getTestData(functionalDefectCountModelList);
			List<Float> resultList = client.estimate(testData);
			double estimatedValue = resultList.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}
			predictResult = (int) Math.round(estimatedValue);
			System.out.println("Functional_Defect_Count: " + predictResult);
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
	private List<FunctionalDefectCountModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
		List<FunctionalDefectCountModel> functionalDefectCountModelList = configService.getFunctionalDefectData(userId);
		return functionalDefectCountModelList;
	}

	private List<ScoredDatum> getTrainingData(List<FunctionalDefectCountModel> functionalDefectCountModelList) throws IOException {
		List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
		for (int counter = 0; counter < functionalDefectCountModelList.size() - 1; counter++) {
			FunctionalDefectCountModel functionalDefectCountModel = functionalDefectCountModelList.get(counter);
			Datum datumTrain = new Datum()
					// .addNumber("Release",
					// functionalDefectCountModel.getRelease())
					.addNumber("KLOC", functionalDefectCountModel.getKiloLinesOfCode())
					.addNumber("TestCaseCount", functionalDefectCountModel.getTestCaseCount())
					.addNumber("ApplicationComplexity", functionalDefectCountModel.getApplicationComplexity())
					.addNumber("DomainKnowledge", functionalDefectCountModel.getDomainKnowledge())
					.addNumber("TechnicalSkills", functionalDefectCountModel.getTechnicalSkills())
					.addNumber("RequirementQueryCount", functionalDefectCountModel.getRequirementQueryCount())
					.addNumber("CodeReviewComments", functionalDefectCountModel.getCodeReviewComments())
					.addNumber("DesignReviewComments", functionalDefectCountModel.getDesignReviewComments());

			trainingData.add(new ScoredDatum(functionalDefectCountModel.getDefectCount(), datumTrain));
		}

		return trainingData;
	}

	private List<Datum> getTestData(List<FunctionalDefectCountModel> functionalDefectCountModelList) throws IOException {
		List<Datum> testData;
		FunctionalDefectCountModel functionalDefectCountModel = functionalDefectCountModelList.get(functionalDefectCountModelList.size()-1);
		Datum datumInput = new Datum()
				// .addNumber("Release",
				// functionalDefectCountModel.getRelease())
				.addNumber("KLOC", functionalDefectCountModel.getKiloLinesOfCode())
				.addNumber("TestCaseCount", functionalDefectCountModel.getTestCaseCount())
				.addNumber("ApplicationComplexity", functionalDefectCountModel.getApplicationComplexity())
				.addNumber("DomainKnowledge", functionalDefectCountModel.getDomainKnowledge())
				.addNumber("TechnicalSkills", functionalDefectCountModel.getTechnicalSkills())
				.addNumber("RequirementQueryCount", functionalDefectCountModel.getRequirementQueryCount())
				.addNumber("CodeReviewComments", functionalDefectCountModel.getCodeReviewComments())
				.addNumber("DesignReviewComments", functionalDefectCountModel.getDesignReviewComments());
		Datum[] testDataArray = { datumInput };
		testData = Arrays.asList(testDataArray);
		return testData;
	}

}
