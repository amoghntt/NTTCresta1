package com.nttdata.web.model;

import java.util.ArrayList;
import java.util.List;

public class Datasets {

	// @Autowired
	private DefectDensityGraphBean[] datasets;
	
	private List<Integer> oldList = new ArrayList<Integer>();
	private List<Integer> defectCountList = new ArrayList<Integer>();
	
	public List<Integer> getOldList() {
		return oldList;
	}

	public void setOldList(List<Integer> oldList) {
		this.oldList = oldList;
	}

	public List<Integer> getDefectCountList() {
		return defectCountList;
	}

	public void setDefectCountList(List<Integer> defectCountList) {
		this.defectCountList = defectCountList;
	}

	public Datasets (int size) {
		this.datasets = new DefectDensityGraphBean[size];
	}

	public DefectDensityGraphBean[] getDatasets() {
		return datasets;
	}

	public void setDatasets(DefectDensityGraphBean[] datasets) {
		this.datasets = datasets;
	}

	
	

}
