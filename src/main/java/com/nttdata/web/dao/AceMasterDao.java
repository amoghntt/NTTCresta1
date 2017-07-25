package com.nttdata.web.dao;

import java.util.List;

import com.nttdata.web.model.TelephonicaBean;

public interface AceMasterDao {
	
	public List<TelephonicaBean> getPieChartData();

	List<TelephonicaBean> getTestEnvironmentORSoftwareData(String category);

}
