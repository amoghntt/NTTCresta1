/*package com.nttdata.web.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import scriptella.driver.spring.EtlExecutorBean;
import scriptella.execution.EtlExecutorException;

public class CalculateLimitsHelper {

	private static final Logger log = LoggerFactory.getLogger(CalculateLimitsHelper.class);

	@Autowired
	@Resource(name = "calculateUclLcl")
	private EtlExecutorBean calculateUclLclExecutorBean;

	public List<int[]> processUCLLCLValue(String metricsId) {
		List<int[]> metricsList = new LinkedList<int[]>();
		try {
			try {
				calculateUclLclExecutorBean.execute();
			} catch (EtlExecutorException etlExecutorException) {
				log.debug("Error executing ETL file" + etlExecutorException);
				etlExecutorException.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return metricsList;

	}

	public EtlExecutorBean getCalculateUclLclExecutorBean() {
		return calculateUclLclExecutorBean;
	}

	public void setCalculateUclLclExecutorBean(EtlExecutorBean calculateUclLclExecutorBean) {
		this.calculateUclLclExecutorBean = calculateUclLclExecutorBean;
	}

}
*/