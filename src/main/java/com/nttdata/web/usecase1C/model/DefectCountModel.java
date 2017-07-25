package com.nttdata.web.usecase1C.model;

public class DefectCountModel {
	
	private Integer defectDensity;
	private Integer kLoc;
	private Integer testCaseCount;
	private Integer applicationComplexity;
	private Integer domainKnowledge;
	private Integer technicalSkills;
	private Integer requirementsQueryCount;
	private Integer codeReviewComments;
	private Integer designReviewComments;
	private Integer release;
	
	public Integer getRelease() {
		return release;
	}
	public void setRelease(Integer release) {
		this.release = release;
	}
	public Integer getkLoc() {
		return kLoc;
	}
	public void setkLoc(Integer kLoc) {
		this.kLoc = kLoc;
	}
	public Integer getTestCaseCount() {
		return testCaseCount;
	}
	public void setTestCaseCount(Integer testCaseCount) {
		this.testCaseCount = testCaseCount;
	}
	public Integer getApplicationComplexity() {
		return applicationComplexity;
	}
	public void setApplicationComplexity(Integer applicationComplexity) {
		this.applicationComplexity = applicationComplexity;
	}
	public Integer getDomainKnowledge() {
		return domainKnowledge;
	}
	public void setDomainKnowledge(Integer domainKnowledge) {
		this.domainKnowledge = domainKnowledge;
	}
	public Integer getTechnicalSkills() {
		return technicalSkills;
	}
	public void setTechnicalSkills(Integer technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	public Integer getRequirementsQueryCount() {
		return requirementsQueryCount;
	}
	public void setRequirementsQueryCount(Integer requirementsQueryCount) {
		this.requirementsQueryCount = requirementsQueryCount;
	}
	public Integer getCodeReviewComments() {
		return codeReviewComments;
	}
	public void setCodeReviewComments(Integer codeReviewComments) {
		this.codeReviewComments = codeReviewComments;
	}
	public Integer getDesignReviewComments() {
		return designReviewComments;
	}
	public void setDesignReviewComments(Integer designReviewComments) {
		this.designReviewComments = designReviewComments;
	}
	
	public Integer getDefectDensity() {
		return defectDensity;
	}
	public void setDefectDensity(Integer defectDensity) {
		this.defectDensity = defectDensity;
	}
	
}
