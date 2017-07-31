package com.nttdata.web.model;

import com.nttdata.web.usecase3.model.DefectLeakageModel;

public class ModuleDetails extends DefectLeakageModel {
	
	private String moduleName;
	private int predictedValue;
	private String category;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getPredictedValue() {
		return predictedValue;
	}

	public void setPredictedValue(int predictedValue) {
		this.predictedValue = predictedValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	

}
