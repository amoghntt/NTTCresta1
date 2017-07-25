package com.nttdata.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.jubatus.helper.JubatusHelper;
import com.nttdata.web.talend.MySQLFetchDefectDensity;
import com.nttdata.web.usecase1.service.FindDefectsServiceUseCase1;

import routines.system.StringUtils;
import scriptella.driver.spring.EtlExecutorBean;
import scriptella.execution.EtlExecutorException;

public class PredictDefectDensityHelper {
	private JubatusHelper jubatusHelper;

	@Autowired
	@Resource(name = "calculateUclLcl")
	private EtlExecutorBean predictDefectDensityExecutorBean;

	public List<Integer> processDefectDensity(String predictionId, String metricsId, int projectId, String userId) {
		List<Integer> metricsList = new ArrayList<Integer>();
		try {
			try {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put("userId", userId);
				properties.put("projectId", StringUtils.valueOf(projectId));
				MySQLFetchDefectDensity defectDensityJob = new MySQLFetchDefectDensity();
				defectDensityJob.runJob(new String[] {});
				FindDefectsServiceUseCase1 fd = new FindDefectsServiceUseCase1();
				System.out.println(userId);
				metricsList.add(fd.predictData(Integer.parseInt(userId), predictionId));
			} catch (EtlExecutorException etlExecutorException) {
				etlExecutorException.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return metricsList;

	}

	public JubatusHelper getJubatusHelper() {
		return jubatusHelper;
	}

	public void setJubatusHelper(JubatusHelper jubatusHelper) {
		this.jubatusHelper = jubatusHelper;
	}

	public EtlExecutorBean getPredictDefectDensityExecutorBean() {
		return predictDefectDensityExecutorBean;
	}

	public void setPredictDefectDensityExecutorBean(EtlExecutorBean predictDefectDensityExecutorBean) {
		this.predictDefectDensityExecutorBean = predictDefectDensityExecutorBean;
	}

}
