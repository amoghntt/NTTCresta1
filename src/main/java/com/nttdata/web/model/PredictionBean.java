package com.nttdata.web.model;

import java.util.ArrayList;
import java.util.List;

public class PredictionBean {
	private String predictionCode;
	private String predictionId;
	private String predictDescription;
	private List<MetricsBean> metricsList = new ArrayList<MetricsBean>(); 
	
	private TrendParameterBean trendParameterBean = new TrendParameterBean();
	
	public TrendParameterBean getTrendParameterBean() {
		return trendParameterBean;
	}
	public void setTrendParameterBean(TrendParameterBean trendParameterBean) {
		this.trendParameterBean = trendParameterBean;
	}
	public String getPredictDescription() {
		return predictDescription;
	}
	public void setPredictDescription(String predictDescription) {
		this.predictDescription = predictDescription;
	}
	private ProjectBean projectBean = new ProjectBean();
	private List<MetricsBean> metricsBeanList = new ArrayList<MetricsBean>();
	private List<TrendParameterBean> trendParametersList = new ArrayList<TrendParameterBean>();
	
	
	public List<MetricsBean> getMetricsList() {
		return metricsList;
	}
	public void setMetricsList(List<MetricsBean> metricsList) {
		this.metricsList = metricsList;
	}

	
	public String getPredictionId() {
		return predictionId;
	}
	public void setPredictionId(String predictionId) {
		this.predictionId = predictionId;
	}
	public ProjectBean getProjectBean() {
		return projectBean;
	}
	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}
	public List<MetricsBean> getMetricsBean() {
		return metricsBeanList;
	}
	public void setMetricsBean(List<MetricsBean> metricsBean) {
		this.metricsBeanList = metricsBean;
	}
	public List<TrendParameterBean> getTrendParameters() {
		return trendParametersList;
	}
	public void setTrendParameters(List<TrendParameterBean> trendParameters) {
		this.trendParametersList = trendParameters;
	}
	public String getPredictionCode() {
		return predictionCode;
	}
	public void setPredictionCode(String predictionCode) {
		this.predictionCode = predictionCode;
	}
	
}
