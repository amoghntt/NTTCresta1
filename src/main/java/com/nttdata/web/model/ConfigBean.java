package com.nttdata.web.model;

import java.util.ArrayList;
import java.util.List;

public class ConfigBean extends UserBean {

	private String metricsCode;
	private String metricsDescription;
	private float weightage;

	private int pred_metrics_mapping_id;
	private PredictionBean predictBean = new PredictionBean();
	public AlgorithmBean getAlgorithmBean() {
		return algorithmBean;
	}

	public void setAlgorithmBean(AlgorithmBean algorithmBean) {
		this.algorithmBean = algorithmBean;
	}

	private ProjectBean projectBean = new ProjectBean();
	private List<PredictionBean> predictionBeanList = new ArrayList<PredictionBean>();

	private List<ProjectBean> projectBeanList = new ArrayList<ProjectBean>();
	
	private List<AlgorithmBean> algorithmBeanList = new ArrayList<AlgorithmBean>();
	
	private AlgorithmBean algorithmBean = new AlgorithmBean();
	
	public List<AlgorithmBean> getAlgorithmBeanList() {
		return algorithmBeanList;
	}

	public void setAlgorithmBeanList(List<AlgorithmBean> algorithmBeanList) {
		this.algorithmBeanList = algorithmBeanList;
	}

	private int algorithmId;
	private String algorithmCode;
	

	public int getAlgorithmId() {
		return algorithmId;
	}

	public void setAlgorithmId(int algorithmId) {
		this.algorithmId = algorithmId;
	}

	public String getAlgorithmCode() {
		return algorithmCode;
	}

	public void setAlgorithmCode(String algorithmCode) {
		this.algorithmCode = algorithmCode;
	}

	public PredictionBean getPredictBean() {
		return predictBean;
	}

	public void setPredictBean(PredictionBean predictBean) {
		this.predictBean = predictBean;
	}

	public ProjectBean getProjectBean() {
		return projectBean;
	}

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

	public List<PredictionBean> getPredictionBeanList() {
		return predictionBeanList;
	}

	public void setPredictionBeanList(List<PredictionBean> predictionBeanList) {
		this.predictionBeanList = predictionBeanList;
	}

	public String getMetricsCode() {
		return metricsCode;
	}

	public void setMetricsCode(String metricsCode) {
		this.metricsCode = metricsCode;
	}

	public String getMetricsDescription() {
		return metricsDescription;
	}

	public void setMetricsDescription(String metricsDescription) {
		this.metricsDescription = metricsDescription;
	}

	public void setWeightage(float weightage) {
		this.weightage = weightage;
	}

	public float getWeightage() {
		return weightage;
	}

	public int getpred_metrics_mapping_id() {
		return pred_metrics_mapping_id;
	}

	public void setpred_metrics_mapping_id(int pred_metrics_mapping_id) {
		this.pred_metrics_mapping_id = pred_metrics_mapping_id;
	}

	public List<ProjectBean> getProjectBeanList() {
		return projectBeanList;
	}

	public void setProjectBeanList(List<ProjectBean> projectBeanList) {
		this.projectBeanList = projectBeanList;
	}
}
