package com.nttdata.web.model;

import java.util.ArrayList;
import java.util.List;

public class GraphBean {
	private Datasets dataSets;
	private Labels xAxisLabels;
	private String[] axisNames;
	private List<Integer> oldList = new ArrayList<Integer>();
	private List<Integer> defectCountList = new ArrayList<Integer>();
	
	private List<Integer> releaseList = new ArrayList<Integer>();
	
	
	public Labels getxAxisLabels() {
		return xAxisLabels;
	}
	public void setxAxisLabels(Labels xAxisLabels) {
		this.xAxisLabels = xAxisLabels;
	}
	
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
	public Datasets getDataSets() {
		return dataSets;
	}
	public void setDataSets(Datasets dataSets) {
		this.dataSets = dataSets;
	}
	
	public String[] getAxisNames() {
		return axisNames;
	}
	public void setAxisNames(String[] axisNames) {
		this.axisNames = axisNames;
	}
	public List<Integer> getReleaseList() {
		return releaseList;
	}
	public void setReleaseList(List<Integer> releaseList) {
		this.releaseList = releaseList;
	}

}
