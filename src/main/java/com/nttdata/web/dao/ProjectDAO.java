package com.nttdata.web.dao;

import java.util.List;

import com.nttdata.web.model.ProjectBean;

public interface ProjectDAO {
	public List<ProjectBean> getProjectListForUser(String userId);
}
