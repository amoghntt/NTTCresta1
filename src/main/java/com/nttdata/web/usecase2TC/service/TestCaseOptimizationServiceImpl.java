package com.nttdata.web.usecase2TC.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.usecase2TC.model.TestCaseOptimizationBean;
import com.nttdata.web.usecase3A.service.TestCaseCoverageServiceImpl;

@Service
public class TestCaseOptimizationServiceImpl implements TestCaseOptimizationService {
	private static Logger log = LoggerFactory.getLogger(TestCaseCoverageServiceImpl.class);

	@Override
	public List<TestCaseOptimizationBean> getOptimizationResult() {
		List<ArrayList<Double>> resultList = Java2PythonExecutor
				.getTestOptimization(ScikitConstants.UC2_TEST_CASE_OPTIMIZATION_SCRIPT);
		log.info("resultList = " + resultList);
		if(resultList !=null){
		int testId = 1;
		DecimalFormat df = new DecimalFormat("#.00");
		Double percentageResult = 0.0;
		List<TestCaseOptimizationBean> testOptimizationBeanList = new ArrayList<TestCaseOptimizationBean>();
		for (ArrayList<Double> element : resultList) {
			TestCaseOptimizationBean testOptimizationBean = new TestCaseOptimizationBean();
			testOptimizationBean.setTestCaseId("TC" + testId);
			List<Double> testSimilarityList = new ArrayList<Double>();
			for (Double element1 : element) {
				percentageResult = Double.parseDouble(df.format(element1 * 100));
				testSimilarityList.add(percentageResult);
			}
			testOptimizationBean.setTestCaseSimilarity(testSimilarityList);
			testOptimizationBeanList.add(testOptimizationBean);
			testId = testId + 1;
		}
		return testOptimizationBeanList;
		}else{
			List<TestCaseOptimizationBean> testOptimizationBeanList = new ArrayList<TestCaseOptimizationBean>();
			testOptimizationBeanList = null;
			return testOptimizationBeanList;
		}
	}

	@Override
	public boolean checkFileExtension(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);
		if(extension.equals("xls")|| extension.equals("xlsx")||extension.equals("xlsm")){
		return true;	
		}
		return false;
	}
}
