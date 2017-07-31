package com.nttdata.web.model;

public class PredictedModel {
	
	private int defectDensity;
	private int defectLeakage;
	private int defectRejection;
	private int ucl;
	private int lcl;
	public int getDefectDensity() {
		return defectDensity;
	}
	public void setDefectDensity(int defectDensity) {
		this.defectDensity = defectDensity;
	}
	public int getDefectLeakage() {
		return defectLeakage;
	}
	public void setDefectLeakage(int defectLeakage) {
		this.defectLeakage = defectLeakage;
	}
	public int getDefectRejection() {
		return defectRejection;
	}
	public void setDefectRejection(int defectRejection) {
		this.defectRejection = defectRejection;
	}
	public int getUcl() {
		return ucl;
	}
	public void setUcl(int ucl) {
		this.ucl = ucl;
	}
	public int getLcl() {
		return lcl;
	}
	public void setLcl(int lcl) {
		this.lcl = lcl;
	}
	
	
	
	

}
