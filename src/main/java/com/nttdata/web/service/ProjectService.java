package com.nttdata.web.service;

import java.util.List;

import com.nttdata.web.model.ProjectBean;

public interface ProjectService {
	public List<ProjectBean> getProjectListForUser(String userId);
}
