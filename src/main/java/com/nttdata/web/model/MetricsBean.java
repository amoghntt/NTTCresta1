package com.nttdata.web.model;

public class MetricsBean extends PredictionBean {
	private String metricsName;
	private int metricsId;
	private String metricsCode;
	private String metricsDescription;
	private int[] ucl;
	private int[] lcl;
	private Double weightage;
	public String getMetricsName() {
		return metricsName;
	}
	public void setMetricsName(String metricsName) {
		this.metricsName = metricsName;
	}
	public int getMetricsId() {
		return metricsId;
	}
	public void setMetricsId(int metricsId) {
		this.metricsId = metricsId;
	}
	public String getMetricsDescription() {
		return metricsDescription;
	}
	public void setMetricsDescription(String metricsDescription) {
		this.metricsDescription = metricsDescription;
	}
	
	public String getMetricsCode() {
		return metricsCode;
	}
	public void setMetricsCode(String metricsCode) {
		this.metricsCode = metricsCode;
	}
	public Double getWeightage() {
		return weightage;
	}
	public void setWeightage(Double weightage) {
		this.weightage = weightage;
	}
	public int[] getUcl() {
		return ucl;
	}
	public void setUcl(int[] ucl) {
		this.ucl = ucl;
	}
	public int[] getLcl() {
		return lcl;
	}
	public void setLcl(int[] lcl) {
		this.lcl = lcl;
	}
	


}
