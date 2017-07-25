package com.nttdata.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.common.CommonService;
import com.nttdata.web.model.AlgorithmBean;
import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.ProjectBean;
import com.nttdata.web.service.ConfigService;

@Controller
public class MetricsController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/metrics", method = RequestMethod.POST)
	public ModelAndView getMetricsFromUser(@ModelAttribute("configBean") ConfigBean configBean,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String predictionId = configBean.getPredictBean().getPredictionId();
		session.setAttribute("SELECTEDPROJECT", configBean.getProjectBean().getRedmineProjectId());
		PredictionBean predictBean = configBean.getPredictBean();

		Map<String, String> resultValidatePredictDataMap = configService.validatePredictData(configBean);
		session.setAttribute("SELECTEDPREDICTION", predictionId);
		session.setAttribute("SELECTEDTRENDPARAMETER", predictBean.getTrendParameterBean().getParameterId());

		Map<String, List<MetricsBean>> metricBeanList = (Map<String, List<MetricsBean>>) session
				.getAttribute("PREDICTMETRICMAPPING");
		List<MetricsBean> metricList = getMetricsListFromPredictionId(metricBeanList, predictionId);
		predictBean.setMetricsList(metricList);
		configBean.setPredictBean(predictBean);
		configBean.getProjectBean()
				.setProjectName(commonService.getProjectName(configBean.getProjectBean().getRedmineProjectId(),
						(List<ProjectBean>) session.getAttribute("PROJECTLIST")));
		configBean.getPredictBean().setPredictDescription(commonService.getPredictionName(predictionId,
				(List<PredictionBean>) session.getAttribute("PREDICTLIST")));
		configBean.setAlgorithmBeanList((List<AlgorithmBean>) session.getAttribute("ALGORITHMLIST"));
		session.setAttribute("SELECTEDPROJECTNAME", configBean.getProjectBean().getProjectName());
		
		int metricsSize = configBean.getPredictBean().getMetricsList().size();
		ModelAndView modelAndView = null;
		if (predictionId.equals("DEFECT_DENSITY")) {
			modelAndView = new ModelAndView("predictDefectDensityMetricsForUseCase1");
		} else if (predictionId.equals("DEFECTIVE_MODULES")) {
			modelAndView = new ModelAndView("defectivemodulesmetrics");
		} else if (predictionId.equals("DEFECT_LEAKAGE")) {
		    modelAndView = new ModelAndView("defectleakagemetrics");
		} else if (predictionId.equals("DEFECT_ACCEPTANCE_RATE")) {
			modelAndView = new ModelAndView("defectacceptancemetrics");
		} else if (predictionId.equals("DEFECT_DEFERRAL_RATE")) {
			modelAndView = new ModelAndView("defectDefferalmetrics");
		} else if (predictionId.equals("DEFECT_COUNT")) {
			modelAndView = new ModelAndView("defectcountmetrics");
		} else if (predictionId.equals("FUNCTIONAL_DEFECT_COUNT")) {
			modelAndView = new ModelAndView("functionaldefectmetrics");
		} else if (predictionId.equals("DEFECT_COUNT1")) {
			modelAndView = new ModelAndView("defectcountboa");
		}
		modelAndView.addObject("configBean", configBean);
		modelAndView.addObject("algorithmList",session.getAttribute("ALGORITHMLIST"));
		modelAndView.addObject("metricsSize", metricsSize);

		return modelAndView;
	}

	private List<MetricsBean> getMetricsListFromPredictionId(Map<String, List<MetricsBean>> metricBeanList,
			String predictionId) {
		for (Entry<String, List<MetricsBean>> entry : metricBeanList.entrySet()) {
			if (entry.getKey().equals(predictionId)) {
				return entry.getValue();
			}
		}
		return new ArrayList<MetricsBean>();
	}
}
