package com.nttdata.web.usecase1.service;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase1.model.DefectDensityModel;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase1 {
	@Autowired
	private ConfigService configService;
	public FindDefectsServiceUseCase1() {

	}

	public int predictData(int userId, String predictionCode) throws Exception {
		int predictResult = 0;
		JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
				"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DD);
		try {
			System.out.flush();
			System.out.println("\n");
			System.out.println("\n");
//			predictResult = start(userId, predictionCode);
			predictResult = startJubatusTelephonica(userId, predictionCode);
			JubatusUtils.stopJubatus(CrestaQueryConstants.PRED_PORT_DD);

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
			List<DefectDensityModel> defectDensityModelList = fetchDataFromDb(userId, predictionCode);
			List<ScoredDatum> trainingData = getTrainingData(defectDensityModelList);
			client.train(trainingData);
			client.save(CrestaConstants.MODEL_REGRESSION_UC1_DEFECT_DENSITY);
			client.load(CrestaConstants.MODEL_REGRESSION_UC1_DEFECT_DENSITY);
			List<Datum> testData = getTestData(defectDensityModelList);
			List<Float> resultList = client.estimate(testData);
			double estimatedValue = resultList.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}
			predictResult = (int) Math.round(estimatedValue);
			System.out.println("Defect_Density: " + predictResult);
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
			List<DefectDensityModelTelephonica> defectDensityModelList = fetchDataFromDbTelephonica(userId, predictionCode);
			List<ScoredDatum> trainingData = getTrainingDataTelephonica(defectDensityModelList);
			client.train(trainingData);
			client.save(CrestaConstants.MODEL_REGRESSION_UC1_DEFECT_DENSITY);
			client.load(CrestaConstants.MODEL_REGRESSION_UC1_DEFECT_DENSITY);
			List<Datum> testData = getTestDataTelephonica(defectDensityModelList);
			List<Float> resultList = client.estimate(testData);
			double estimatedValue = resultList.get(0).doubleValue();
			if (estimatedValue < 0.0) {
				estimatedValue = 0.0;
			}
			predictResult = (int) Math.round(estimatedValue);
			System.out.println("Defect_Density: " + predictResult);
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
			client = new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DD,
					CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	private List<DefectDensityModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
		List<DefectDensityModel> defectDensityModelList = configService.getDefectDensityDataForUseCase1(userId);
		return defectDensityModelList;
	}
	
	private List<DefectDensityModelTelephonica> fetchDataFromDbTelephonica(int userId, String predictionCode) throws IOException {
		List<DefectDensityModelTelephonica> defectDensityModelList = configService.getDefectDensityDataForUseCase1Telephonica(userId);
		return defectDensityModelList;
	}

	private List<ScoredDatum> getTrainingData(List<DefectDensityModel> defectDensityModelList) throws IOException {
		List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
		/*for (int counter = 0; counter < defectDensityModelList.size() - 1; counter++) {*/
		for (int counter = 0; counter < defectDensityModelList.size(); counter++) {
			DefectDensityModel defectDensityModel = defectDensityModelList.get(counter);
			Datum datumTrain = new Datum()
					// .addNumber("Release", defectDensityModel.getRelease())
					.addNumber("KLOC", defectDensityModel.getKiloLinesOfCode())
					.addNumber("TestCaseCount", defectDensityModel.getTestCaseCount())
					.addNumber("ApplicationComplexity", defectDensityModel.getApplicationComplexity())
					.addNumber("DomainKnowledge", defectDensityModel.getDomainKnowledge())
					.addNumber("TechnicalSkills", defectDensityModel.getTechnicalSkills())
					.addNumber("RequirementQueryCount", defectDensityModel.getRequirementQueryCount())
					.addNumber("CodeReviewComments", defectDensityModel.getCodeReviewComments())
					.addNumber("DesignReviewComments", defectDensityModel.getDesignReviewComments());

			trainingData.add(new ScoredDatum(defectDensityModel.getDefectDensity(), datumTrain));
		}

		return trainingData;
	}
	
	
	private List<ScoredDatum> getTrainingDataTelephonica(List<DefectDensityModelTelephonica> defectDensityModelList) throws IOException {
		List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
		int is=0;
		/*for (int counter = 0; counter < defectDensityModelList.size() - 1; counter++) {*/
		for (int counter = 0; counter < defectDensityModelList.size(); counter++) {
			DefectDensityModelTelephonica defectDensityModelTelephonica = defectDensityModelList.get(counter);
			Datum datumTrain = new Datum()
					// .addNumber("Release", defectDensityModel.getRelease())
					.addNumber("DefectCount", defectDensityModelTelephonica.getDefectCount())
					.addNumber("rel_id", defectDensityModelTelephonica.getRel_Id())
					.addNumber("KLOC", defectDensityModelTelephonica.getKiloLinesOfCode())
					.addNumber("TestCaseCount", defectDensityModelTelephonica.getTestCaseCount())
					.addNumber("ApplicationComplexity", defectDensityModelTelephonica.getApplicationComplexity())
					.addNumber("DomainKnowledge", defectDensityModelTelephonica.getDomainKnowledge())
					.addNumber("TechnicalSkills", defectDensityModelTelephonica.getTechnicalSkills())
					.addNumber("RequirementQueryCount", defectDensityModelTelephonica.getRequirementQueryCount())
					.addNumber("CodeReviewComments", defectDensityModelTelephonica.getCodeReviewComments())
					.addNumber("DesignReviewComments", defectDensityModelTelephonica.getDesignReviewComments())
					.addNumber("BG_Severity", defectDensityModelTelephonica.getBg_Severity())
					.addNumber("BG_Priority", defectDensityModelTelephonica.getBg_Priority());
			
			trainingData.add(new ScoredDatum(defectDensityModelTelephonica.getDefectDensity(), datumTrain));
		}

		return trainingData;
	}
	

	private List<Datum> getTestData(List<DefectDensityModel> defectDensityModelList) throws IOException {
		List<Datum> testData;
		DefectDensityModel defectDensityModel = defectDensityModelList.get(defectDensityModelList.size() - 1);
		Datum datumInput = new Datum()
				// .addNumber("Release", defectDensityModel.getRelease())
				.addNumber("KLOC", defectDensityModel.getKiloLinesOfCode())
				.addNumber("TestCaseCount", defectDensityModel.getTestCaseCount())
				.addNumber("ApplicationComplexity", defectDensityModel.getApplicationComplexity())
				.addNumber("DomainKnowledge", defectDensityModel.getDomainKnowledge())
				.addNumber("TechnicalSkills", defectDensityModel.getTechnicalSkills())
				.addNumber("RequirementQueryCount", defectDensityModel.getRequirementQueryCount())
				.addNumber("CodeReviewComments", defectDensityModel.getCodeReviewComments())
				.addNumber("DesignReviewComments", defectDensityModel.getDesignReviewComments());
		Datum[] testDataArray = { datumInput };
		testData = Arrays.asList(testDataArray);
		return testData;
	}
	
	
	private List<Datum> getTestDataTelephonica(List<DefectDensityModelTelephonica> defectDensityModelList) throws IOException {
		List<Datum> testData;
		DefectDensityModelTelephonica defectDensityModelTelephonica = defectDensityModelList.get(defectDensityModelList.size() - 1);
		Datum datumInput = new Datum()
				/*.addNumber("Release", defectDensityModel.getRelease())*/
				.addNumber("DefectCount", defectDensityModelTelephonica.getDefectCount())
				.addNumber("rel_id", defectDensityModelTelephonica.getRel_Id())
				.addNumber("KLOC", defectDensityModelTelephonica.getKiloLinesOfCode())
				.addNumber("TestCaseCount", defectDensityModelTelephonica.getTestCaseCount())
				.addNumber("ApplicationComplexity", defectDensityModelTelephonica.getApplicationComplexity())
				.addNumber("DomainKnowledge", defectDensityModelTelephonica.getDomainKnowledge())
				.addNumber("TechnicalSkills", defectDensityModelTelephonica.getTechnicalSkills())
				.addNumber("RequirementQueryCount", defectDensityModelTelephonica.getRequirementQueryCount())
				.addNumber("CodeReviewComments", defectDensityModelTelephonica.getCodeReviewComments())
				.addNumber("DesignReviewComments", defectDensityModelTelephonica.getDesignReviewComments())
				.addNumber("BG_Severity", defectDensityModelTelephonica.getBg_Severity())
				.addNumber("BG_Priority", defectDensityModelTelephonica.getBg_Priority());
				
		Datum[] testDataArray = { datumInput };
		testData = Arrays.asList(testDataArray);
		return testData;
	}
	
	

}
