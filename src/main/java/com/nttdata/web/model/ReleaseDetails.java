package com.nttdata.web.model;

public class ReleaseDetails {
	private String release;
	private String actualValue;
	private String predictedValue;
	private String accuracy;
	private Integer releaseid;
	private Integer predictedValueInt;
	
	public Integer getReleaseid() {
		return releaseid;
	}
	public void setReleaseid(Integer releaseid) {
		this.releaseid = releaseid;
	}
	public Integer getPredictedValueInt() {
		return predictedValueInt;
	}
	public void setPredictedValueInt(Integer predictedValueInt) {
		this.predictedValueInt = predictedValueInt;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	public String getPredictedValue() {
		return predictedValue;
	}
	public void setPredictedValue(String predictedValue) {
		this.predictedValue = predictedValue;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

}
