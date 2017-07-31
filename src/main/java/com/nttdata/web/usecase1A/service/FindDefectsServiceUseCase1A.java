package com.nttdata.web.usecase1A.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.rpc.loop.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModel;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.common.Datum;
import us.jubat.regression.RegressionClient;
import us.jubat.regression.ScoredDatum;

@Service
public class FindDefectsServiceUseCase1A {
	
	    @Autowired
		private ConfigService configService;
	    
	    
	    public FindDefectsServiceUseCase1A() {

		}

		public int predictData(int userId, String predictionCode) throws Exception {
			int predictResult = 0;
			JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
					"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", CrestaQueryConstants.PRED_PORT_DA);
			try {
				System.out.flush();
				System.out.println("\n");
				System.out.println("\n");
				predictResult = startTelephonica(userId, predictionCode);
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
				List<DefectAcceptanceModel> defectAcceptanceModelList = fetchDataFromDb(userId, predictionCode);
				List<ScoredDatum> trainingData = getTrainingData(defectAcceptanceModelList);
				client.train(trainingData);
				client.save(CrestaConstants.MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE);
				client.load(CrestaConstants.MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE);
				List<Datum> testData = getTestData(defectAcceptanceModelList);
				List<Float> resultList = client.estimate(testData);
				double estimatedValue = resultList.get(0).doubleValue();
				if (estimatedValue < 0.0) {
					estimatedValue = 0.0;
				}
				predictResult = (int) Math.round(estimatedValue);
				System.out.println("Defect_Acceptance: " + predictResult);
				System.out.println("\n");
				System.out.println("\n");
			} finally {
				if (null != client) {
					client.getClient().close();
				}
			}
			return predictResult;
		}
		
		
		public int startTelephonica(int userId, String predictionCode) throws Exception {
			RegressionClient client = null;
			int predictResult = 0;

			try {
				client = getJubatusClient();
				List<DefectAcceptanceModelTelephonica> defectAcceptanceModelList = fetchDataFromDbTelephonica(userId, predictionCode);
				List<ScoredDatum> trainingData = getTrainingDataTelephonica(defectAcceptanceModelList);
				client.train(trainingData);
				client.save(CrestaConstants.MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE);
				client.load(CrestaConstants.MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE);
				List<Datum> testData = getTestDataTelephonica(defectAcceptanceModelList);
				List<Float> resultList = client.estimate(testData);
				double estimatedValue = resultList.get(0).doubleValue();
				if (estimatedValue < 0.0) {
					estimatedValue = 0.0;
				}
				predictResult = (int) Math.round(estimatedValue);
				System.out.println("Defect_Acceptance: " + predictResult);
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
						client =  new RegressionClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PRED_PORT_DA, CrestaQueryConstants.PRED_NAME, CrestaQueryConstants.CLIENT_TIMEOUT);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
			return client;
		}
		
		private List<DefectAcceptanceModel> fetchDataFromDb(int userId, String predictionCode) throws IOException {
			List<DefectAcceptanceModel> defectAcceptanceModelList = configService.getDefectAcceptanceData(userId);
			return defectAcceptanceModelList;
		}
		
		
		private List<DefectAcceptanceModelTelephonica> fetchDataFromDbTelephonica(int userId, String predictionCode) throws IOException {
			List<DefectAcceptanceModelTelephonica> defectAcceptanceModelList = configService.getDefectAcceptanceDataTelephonica(userId);
			return defectAcceptanceModelList;
		}
		

		private List<ScoredDatum> getTrainingData(List<DefectAcceptanceModel> defectAcceptanceModelList ) throws IOException {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			  for (int counter = 0; counter <defectAcceptanceModelList.size()-1; counter ++ ) {
		    	   DefectAcceptanceModel defectAcceptanceModel = defectAcceptanceModelList.get(counter);
		    	   Datum datumTrain = new Datum()
		                    .addNumber("KLOC", defectAcceptanceModel.getKiloLinesOfCode())
		                    .addNumber("TestCaseCount", defectAcceptanceModel.getTestCaseCount())
		                    .addNumber("ApplicationComplexity", defectAcceptanceModel.getApplicationComplexity())
		                    .addNumber("DomainKnowledge", defectAcceptanceModel.getDomainKnowledge())
		                    .addNumber("TechnicalSkills", defectAcceptanceModel.getTechnicalSkills())
		                    .addNumber("RequirementQueryCount", defectAcceptanceModel.getRequirementQueryCount())
		                    .addNumber("CodeReviewComments", defectAcceptanceModel.getCodeReviewComments())
		                    .addNumber("DesignReviewComments", defectAcceptanceModel.getDesignReviewComments());

				trainingData.add(new ScoredDatum(defectAcceptanceModel.getDefectAcceptance(), datumTrain));
			}

			return trainingData;
		}
		
		
		private List<ScoredDatum> getTrainingDataTelephonica(List<DefectAcceptanceModelTelephonica> defectAcceptanceModelList ) throws IOException {
			List<ScoredDatum> trainingData = new ArrayList<ScoredDatum>();
			  for (int counter = 0; counter <defectAcceptanceModelList.size(); counter ++ ) {
				  DefectAcceptanceModelTelephonica DefectAcceptanceModelTelephonica = defectAcceptanceModelList.get(counter);
		    	   Datum datumTrain = new Datum()
		                    .addNumber("KLOC", DefectAcceptanceModelTelephonica.getKiloLinesOfCode())
		                    .addNumber("TestCaseCount", DefectAcceptanceModelTelephonica.getTestCaseCount())
		                    .addNumber("ApplicationComplexity", DefectAcceptanceModelTelephonica.getApplicationComplexity())
		                    .addNumber("DomainKnowledge", DefectAcceptanceModelTelephonica.getDomainKnowledge())
		                    .addNumber("TechnicalSkills", DefectAcceptanceModelTelephonica.getTechnicalSkills())
		                    .addNumber("RequirementQueryCount", DefectAcceptanceModelTelephonica.getRequirementQueryCount())
		                    .addNumber("CodeReviewComments", DefectAcceptanceModelTelephonica.getCodeReviewComments())
		                    .addNumber("DesignReviewComments", DefectAcceptanceModelTelephonica.getDesignReviewComments());

				trainingData.add(new ScoredDatum(DefectAcceptanceModelTelephonica.getDefectAcceptance(), datumTrain));
			}

			return trainingData;
		}
		

		private List<Datum> getTestData(List<DefectAcceptanceModel> defectAcceptanceModelList) throws IOException {
			List<Datum> testData;

	        DefectAcceptanceModel defectAcceptanceModel = defectAcceptanceModelList.get(defectAcceptanceModelList.size()-1);
	        Datum datumInput = new Datum()
                    .addNumber("KLOC", defectAcceptanceModel.getKiloLinesOfCode())
                    .addNumber("TestCaseCount", defectAcceptanceModel.getTestCaseCount())
                    .addNumber("ApplicationComplexity", defectAcceptanceModel.getApplicationComplexity())
                    .addNumber("DomainKnowledge", defectAcceptanceModel.getDomainKnowledge())
                    .addNumber("TechnicalSkills", defectAcceptanceModel.getTechnicalSkills())
                    .addNumber("RequirementQueryCount", defectAcceptanceModel.getRequirementQueryCount())
                    .addNumber("CodeReviewComments", defectAcceptanceModel.getCodeReviewComments())
                    .addNumber("DesignReviewComments", defectAcceptanceModel.getDesignReviewComments());
	        Datum[] testDataArray = {datumInput};
			testData = Arrays.asList(testDataArray);
			return testData;
		}
		
		
		private List<Datum> getTestDataTelephonica(List<DefectAcceptanceModelTelephonica> defectAcceptanceModelList) throws IOException {
			List<Datum> testData;

			DefectAcceptanceModelTelephonica defectAcceptanceModelTelephonica = defectAcceptanceModelList.get(defectAcceptanceModelList.size()-1);
	        Datum datumInput = new Datum()
                    .addNumber("KLOC", defectAcceptanceModelTelephonica.getKiloLinesOfCode())
                    .addNumber("TestCaseCount", defectAcceptanceModelTelephonica.getTestCaseCount())
                    .addNumber("ApplicationComplexity", defectAcceptanceModelTelephonica.getApplicationComplexity())
                    .addNumber("DomainKnowledge", defectAcceptanceModelTelephonica.getDomainKnowledge())
                    .addNumber("TechnicalSkills", defectAcceptanceModelTelephonica.getTechnicalSkills())
                    .addNumber("RequirementQueryCount", defectAcceptanceModelTelephonica.getRequirementQueryCount())
                    .addNumber("CodeReviewComments", defectAcceptanceModelTelephonica.getCodeReviewComments())
                    .addNumber("DesignReviewComments", defectAcceptanceModelTelephonica.getDesignReviewComments());
	        Datum[] testDataArray = {datumInput};
			testData = Arrays.asList(testDataArray);
			return testData;
		}
		
		

}
