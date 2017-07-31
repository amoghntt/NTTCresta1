package com.nttdata.web.usecase1A.model;

public class DefectAcceptanceModelTelephonica {
	
	
	private Integer release;
	private Integer defectAcceptance;
	private Integer kiloLinesOfCode;
	private Integer testCaseCount;
	private Integer applicationComplexity;
	private Integer domainKnowledge;
	private Integer technicalSkills;
	private Integer requirementQueryCount;
	private Integer codeReviewComments;
	private Integer designReviewComments;
	
	public Integer getKiloLinesOfCode() {
		return kiloLinesOfCode;
	}
	public void setKiloLinesOfCode(Integer kiloLinesOfCode) {
		this.kiloLinesOfCode = kiloLinesOfCode;
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
	public Integer getRequirementQueryCount() {
		return requirementQueryCount;
	}
	public void setRequirementQueryCount(Integer requirementQueryCount) {
		this.requirementQueryCount = requirementQueryCount;
	}
	public Integer getRelease() {
		return release;
	}
	public void setRelease(Integer release) {
		this.release = release;
	}
	public Integer getDefectAcceptance() {
		return defectAcceptance;
	}
	public void setDefectAcceptance(Integer defectCount) {
		this.defectAcceptance = defectCount;
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
	

}
