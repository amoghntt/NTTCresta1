package com.nttdata.web.usecase3A.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.nttdata.web.usecase3A.model.TestCaseCoverageBean;

public interface TestCaseCoverageService {

	public void writeRequirementToFile(FileItem item);
	public List<TestCaseCoverageBean> getTestCaseCoverageResult();
}
