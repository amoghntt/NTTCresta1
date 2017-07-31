package com.nttdata.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

import routines.system.StringUtils;

@Service
public class CalculateUCLLCLServiceImpl implements CalculateUCLLCLService {

	@Autowired
	@Qualifier("scriptellaETL")
	// @Qualifier("talendETL")
	ETL etlBean;

	@Autowired
	private ConfigService configService;

	@Override
	public MetricsBean processCalculateUclLcl(int projectId, String metricsId, List<Integer> defectCount) {
		return processCalculateUclLcl("calculateUclLcl", null, metricsId, null, projectId, defectCount);
	}
	
	@Override
	public MetricsBean processCalculateUclLcl1(int projectId, String metricsId, List<DefectLeakageModel> defectCount) {
		return processCalculateUclLcl1("calculateUclLcl", null, metricsId, null, projectId, defectCount);
	}

	@Override
	public MetricsBean processCalculateUclLcl(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId, List<Integer> defectCount) {
		MetricsBean metricsBean = new MetricsBean();
		List<int[]> resultList = etlBean.interactETLForUclLcl(useCaseType, predictionId, metricsId, userId,
				redmineProjectId, defectCount);
		metricsBean.setLcl(resultList.get(0));
		metricsBean.setUcl(resultList.get(1));
		return metricsBean;
	}
	
	@Override
	public MetricsBean processCalculateUclLcl1(String useCaseType, String predictionId, String metricsId, String userId,
			int redmineProjectId,List<DefectLeakageModel> defectCount) {
		MetricsBean metricsBean = new MetricsBean();
		List<int[]> resultList = etlBean.interactETLForUclLcl1(useCaseType, predictionId, metricsId, userId,
				redmineProjectId, defectCount);
		metricsBean.setLcl(resultList.get(0));
		metricsBean.setUcl(resultList.get(1));
		return metricsBean;
	}

	@Override
	public MetricsBean getDefectCountMetricsBean(Integer userId, String predictionCode, Integer metricsId,
			Integer projectId) {
		List<Integer> defectCount = new ArrayList<Integer>();
		MetricsBean metricsBean = new MetricsBean();
		if (metricsId == 13) {
			defectCount = configService.getDefectDeferralCount(userId, predictionCode);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		} else if (metricsId == 4) {
			defectCount = configService.getDefectAcceptanceCount(userId, predictionCode);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		} else if (metricsId == 1 && predictionCode.equals("DEFECT_DENSITY")) {
			defectCount = configService.getDefectDensityCount(userId, predictionCode);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		} else if ((metricsId == 1 || metricsId == 2 || metricsId == 3) && predictionCode.equals("DEFECTIVE_MODULES")) {
			defectCount = configService.getDefectModuleCount(userId, predictionCode,metricsId);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		}else if (metricsId == 15) {
			defectCount = configService.getDefectCount(userId, predictionCode);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		} else if (metricsId == 14) {
			defectCount = configService.getFunctionalDefectDensityCount(userId, predictionCode);
			metricsBean = processCalculateUclLcl(projectId, StringUtils.valueOf(metricsId), defectCount);
		} else if (metricsId == 2) {
			List<DefectLeakageModel> defectDensityList = configService.getDefectLeakageData(userId, predictionCode);
			metricsBean = processCalculateUclLcl1(projectId, StringUtils.valueOf(metricsId),defectDensityList);
		}
		return metricsBean;
	}

}
