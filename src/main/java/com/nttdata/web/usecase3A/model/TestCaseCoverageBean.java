package com.nttdata.web.usecase3A.model;

public class TestCaseCoverageBean {
	private String testCaseId;
	private String testCaseStatus;
	private String percentageCoverage;
	private String requirements;
	
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getTestCaseStatus() {
		return testCaseStatus;
	}
	public void setTestCaseStatus(String testCaseStatus) {
		this.testCaseStatus = testCaseStatus;
	}
	public String getPercentageCoverage() {
		return percentageCoverage;
	}
	public void setPercentageCoverage(String percentageCoverage) {
		this.percentageCoverage = percentageCoverage;
	}
}
