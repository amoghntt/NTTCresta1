package com.nttdata.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.dao.ProjectDAO;
import com.nttdata.web.model.ProjectBean;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	public List<ProjectBean> getProjectListForUser(String userId) {
		List<ProjectBean> projectBeanList = projectDAO.getProjectListForUser(userId);
		return projectBeanList;
	}
	

}