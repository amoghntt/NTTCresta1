package com.nttdata.web.usecase3.model;

public class DefectLeakageModel {
	private Integer release;
	private Integer defectDensity;
	private Integer defectLeakage;
	private Integer defectRejected;

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

	public Integer getDefectLeakage() {
		return defectLeakage;
	}

	public void setDefectLeakage(Integer defectLeakage) {
		this.defectLeakage = defectLeakage;
	}

	public Integer getDefectRejected() {
		return defectRejected;
	}

	public void setDefectRejected(Integer defectRejected) {
		this.defectRejected = defectRejected;
	}

}
