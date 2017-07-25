package com.nttdata.web.model;

public class TelephonicaBean {

	
	private int countOfBugs;
	private String category;
	private int percentageCategory;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getPercentageCategory() {
		return percentageCategory;
	}
	public void setPercentageCategory(int percentageCategory) {
		this.percentageCategory = percentageCategory;
	}
	public int getCountOfBugs() {
		return countOfBugs;
	}
	public void setCountOfBugs(int countOfBugs) {
		this.countOfBugs = countOfBugs;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	

}
