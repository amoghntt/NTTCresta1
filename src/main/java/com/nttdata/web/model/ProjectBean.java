package com.nttdata.web.model;

public class ProjectBean {
	private Integer redmineProjectId;
	private String projectName;
	private String projectDescription;
	private Integer projectId;
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRedmineProjectId() {
		return redmineProjectId;
	}

	public void setRedmineProjectId(Integer redmineProjectId) {
		this.redmineProjectId = redmineProjectId;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
