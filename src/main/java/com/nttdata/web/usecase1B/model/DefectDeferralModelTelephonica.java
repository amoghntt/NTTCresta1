package com.nttdata.web.usecase1B.model;

public class DefectDeferralModelTelephonica {

	private Integer bg_Severity;
	private Integer bg_Priority;
	private Integer rel_Id;
	private Integer relParentId;
	private String rfName;
	private String grGroupName;
	private Integer effortToFixDefect;
	private Integer costToFixDefect;
	private Integer impactOfDefectFix;
	private boolean feasibilityWithinMilestone;
	private Integer availabilityOfBudget;
	private Integer complexityofDefectFix;
	private Integer defectDeferralCount;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getEffortToFixDefect() {
		return effortToFixDefect;
	}

	public void setEffortToFixDefect(Integer effortToFixDefect) {
		this.effortToFixDefect = effortToFixDefect;
	}

	public Integer getCostToFixDefect() {
		return costToFixDefect;
	}

	public void setCostToFixDefect(Integer costToFixDefect) {
		this.costToFixDefect = costToFixDefect;
	}

	public Integer getImpactOfDefectFix() {
		return impactOfDefectFix;
	}

	public void setImpactOfDefectFix(Integer impactOfDefectFix) {
		this.impactOfDefectFix = impactOfDefectFix;
	}

	public boolean isFeasibilityWithinMilestone() {
		return feasibilityWithinMilestone;
	}

	public void setFeasibilityWithinMilestone(boolean feasibilityWithinMilestone) {
		this.feasibilityWithinMilestone = feasibilityWithinMilestone;
	}

	public Integer getAvailabilityOfBudget() {
		return availabilityOfBudget;
	}

	public void setAvailabilityOfBudget(Integer availabilityOfBudget) {
		this.availabilityOfBudget = availabilityOfBudget;
	}

	public Integer getComplexityofDefectFix() {
		return complexityofDefectFix;
	}

	public void setComplexityofDefectFix(Integer complexityofDefectFix) {
		this.complexityofDefectFix = complexityofDefectFix;
	}

	public Integer getDefectDeferralCount() {
		return defectDeferralCount;
	}

	public void setDefectDeferralCount(Integer defectDeferralCount) {
		this.defectDeferralCount = defectDeferralCount;
	}
}
