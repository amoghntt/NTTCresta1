package com.nttdata.web.common;

import java.util.List;

import com.nttdata.web.model.AlgorithmBean;
import com.nttdata.web.model.MetricsBean;

public interface CommonDAO {

	void deleteDataFromTempDensitytable(int userid, String predictionCode);
	
	public void saveData(MetricsBean metricsBean);
	
	public List<AlgorithmBean> getAlgorithmListForUser();
}
