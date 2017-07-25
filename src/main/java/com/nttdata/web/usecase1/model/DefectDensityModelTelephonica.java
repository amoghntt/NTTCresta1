package com.nttdata.web.usecase1.model;

public class DefectDensityModelTelephonica {

	
	private Integer release;
	private Integer defectDensity;
	private Integer kiloLinesOfCode;
	private Integer testCaseCount;
	private Integer applicationComplexity;
	private Integer domainKnowledge;
	private Integer technicalSkills;
	private Integer requirementQueryCount;
	private Integer codeReviewComments;
	private Integer designReviewComments;
	private Integer defectCount;
	
	private String tprName;
	private Integer rel_Id;
	private Integer relParentId;
	private String rfName;
	private String grGroupName;
	private Integer bg_Severity;
	private Integer bg_Priority;
	
	public Integer getDefectCount() {
		return defectCount;
	}
	public void setDefectCount(Integer defectCount) {
		this.defectCount = defectCount;
	}
	
	public Integer getBg_Severity() {
		return bg_Severity;
	}
	public void setBg_Severity(Integer bg_Severity) {
		this.bg_Severity = bg_Severity;
	}
	public Integer getBg_Priority() {
		return bg_Priority;
	}
	public void setBg_Priority(Integer bg_Priority) {
		this.bg_Priority = bg_Priority;
	}
	
	
	public String getTprName() {
		return tprName;
	}
	public void setTprName(String tprName) {
		this.tprName = tprName;
	}
	public Integer getRel_Id() {
		return rel_Id;
	}
	public void setRel_Id(Integer rel_Id) {
		this.rel_Id = rel_Id;
	}
	public Integer getRelParentId() {
		return relParentId;
	}
	public void setRelParentId(Integer relParentId) {
		this.relParentId = relParentId;
	}
	public String getRfName() {
		return rfName;
	}
	public void setRfName(String rfName) {
		this.rfName = rfName;
	}
	public String getGrGroupName() {
		return grGroupName;
	}
	public void setGrGroupName(String grGroupName) {
		this.grGroupName = grGroupName;
	}
	
	public Integer getRelease() {
		return release;
	}
	public void setRelease(Integer release) {
		this.release = release;
	}
	public Integer getDefectDensity() {
		return defectDensity;
	}
	public void setDefectDensity(Integer defectDensity) {
		this.defectDensity = defectDensity;
	}
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
