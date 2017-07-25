package com.nttdata.web.usecase2TC.model;

import java.util.ArrayList;
import java.util.List;

public class TestCaseOptimizationBean {
	
	private String testCaseId;
	private List<Double> testCaseSimilarity = new ArrayList<Double>();
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public List<Double> getTestCaseSimilarity() {
		return testCaseSimilarity;
	}
	public void setTestCaseSimilarity(List<Double> testCaseSimilarity) {
		this.testCaseSimilarity = testCaseSimilarity;
	}
}
