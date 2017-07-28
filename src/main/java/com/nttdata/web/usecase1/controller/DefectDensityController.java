package com.nttdata.web.usecase1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.common.CommonService;
import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.ProjectBean;
import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.usecase1.service.DefectDensityService;
import com.nttdata.web.utils.ColorEnum;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.Utils;

@Controller
public class DefectDensityController {

	@Autowired
	private DefectDensityService defectDensityService;

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/predictDefectDensityForUseCase1", method = RequestMethod.POST)
	public ModelAndView predictUseCase1(@ModelAttribute("configBean") ConfigBean configBean,
			HttpServletRequest request) {
		String predictionId = "1";
		int algorithmId = configBean.getAlgorithmBean().getAlgorithmId();
		List<MetricsBean> metricsList = configBean.getPredictBean().getMetricsList();
		return getPredictResult(metricsList, request, predictionId, algorithmId);
	}

	public ModelAndView getPredictResult(List<MetricsBean> metricsList, HttpServletRequest request, String predictionId,
			int algorithmId) {
		MetricsBean metricBean = metricsList.get(0);
		int[] ucl = metricBean.getUcl();
		int[] lcl = metricBean.getLcl();
		metricBean.setPredictionId(predictionId);
		HttpSession session = request.getSession();
		Integer redmineProjectId = (Integer) session.getAttribute("SELECTEDPROJECT");
		int parameterId = (int) session.getAttribute("SELECTEDTRENDPARAMETER");
		String predictionCode = (String) session.getAttribute("SELECTEDPREDICTION");
		UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
		String userId = userBean.getUserId();
		
		commonService.saveData(metricBean);
		commonService.deleteDataFromTempDensitytable(Integer.parseInt(userBean.getUserId()), predictionCode);

		




ModelAndView modelView = null;
if (algorithmId == 1)
	modelView = new ModelAndView("prediction For All Algorithms");
else
	modelView = new ModelAndView("prediction");

int[][][] graphData = new int[6][][];
graphData = defectDensityService.processTask("predictUseCase1", predictionCode, "NA", userId, redmineProjectId,
		ucl, lcl, algorithmId);
String resultString = commonService.convertToJSON(graphData);
List<Integer> defectCountList = new ArrayList<Integer>();
List<Integer> releaseCountList = new ArrayList<Integer>();

int[][] releaseData = new int[graphData.length][];
releaseData = graphData[2];

for (int i = 0; i < releaseData.length; i++) {
	int[] data = (int[]) releaseData[i];
	releaseCountList.add(data[0]);
	defectCountList.add(data[1]);

}

List<Integer> predictionList = new ArrayList<Integer>();
int[][] predictedData = new int[graphData.length][];
predictedData = graphData[3];

for (int i = 0; i < predictedData.length; i++) {
	int[] data = (int[]) predictedData[i];

	predictionList.add(data[1]);

}

ConfigBean configBean = new ConfigBean();
configBean.getProjectBean().setProjectName(commonService.getProjectName(redmineProjectId,
		(List<ProjectBean>) session.getAttribute("PROJECTLIST")));
configBean.getPredictBean().setPredictDescription(commonService.getPredictionName(predictionCode,
		(List<PredictionBean>) session.getAttribute("PREDICTLIST")));
modelView.addObject("datasets", resultString);

modelView.addObject("projectCode", redmineProjectId);
modelView.addObject("parameterId", parameterId);
modelView.addObject("predictionCode", predictionCode);
modelView.addObject("userId", userId);
modelView.addObject("configBean", configBean);
int predictedValue = predictionList.get(predictionList.size() - 1);
ColorEnum colorEnum = Utils.getColorEnum(ucl[0], lcl[0], predictedValue);
modelView.addObject("predictionColour", colorEnum.getColorValue());
releaseCountList.add(releaseCountList.get(releaseCountList.size() - 1) + 1);

int maxDefect = Collections.max(defectCountList);
maxDefect = (ucl[0] > maxDefect) ? ((ucl[0] > predictedValue) ? ucl[0] : predictedValue)
		: ((maxDefect > predictedValue) ? maxDefect : predictedValue);
int lastStepY = CrestaQueryConstants.STEP_SIZE - (maxDefect % CrestaQueryConstants.STEP_SIZE);
maxDefect = maxDefect + lastStepY;

int minDefect = Collections.min(defectCountList);
minDefect = lcl[0] < minDefect ? (lcl[0] < predictedValue ? lcl[0] : predictedValue)
		: (minDefect < predictedValue ? minDefect : predictedValue);
int firstStep = minDefect % CrestaQueryConstants.STEP_SIZE;
if (firstStep == 0 && minDefect >= CrestaQueryConstants.STEP_SIZE)
	minDefect = minDefect - CrestaQueryConstants.STEP_SIZE;
else if (firstStep == 0 && minDefect < CrestaQueryConstants.STEP_SIZE)
	minDefect = 0;
else
	minDefect = minDefect - firstStep;

int maxRelease = Collections.max(releaseCountList);
maxRelease++;

int minRelease = Collections.min(releaseCountList);

ReleaseDetails currentReleaseDetails = new ReleaseDetails();
currentReleaseDetails.setRelease(releaseCountList.get(releaseCountList.size()-2).toString());
currentReleaseDetails.setActualValue(defectCountList.get(defectCountList.size()-1).toString());
currentReleaseDetails.setPredictedValue(CrestaQueryConstants.NOT_APPLICABLE);
currentReleaseDetails.setAccuracy(CrestaQueryConstants.NOT_APPLICABLE);

List<ReleaseDetails> releaseDetailsList = new ArrayList<ReleaseDetails>();
ReleaseDetails nextReleaseDetails = new ReleaseDetails();
nextReleaseDetails.setRelease(releaseCountList.get(releaseCountList.size()-1).toString());
nextReleaseDetails.setActualValue(CrestaQueryConstants.NOT_APPLICABLE);
nextReleaseDetails.setPredictedValue(Integer.toString(predictedValue));
nextReleaseDetails.setAccuracy(CrestaQueryConstants.NOT_APPLICABLE);
releaseDetailsList.add(currentReleaseDetails);
releaseDetailsList.add(nextReleaseDetails);

modelView.addObject("maxYValue", maxDefect);
modelView.addObject("minYValue", minDefect);
modelView.addObject("maxXValue", maxRelease);
modelView.addObject("minXValue", minRelease);
modelView.addObject("xLabel", CrestaQueryConstants.RELEASE);
modelView.addObject("yLabel", CrestaQueryConstants.DEFECT_DENSITY);
modelView.addObject("releaseDetails", releaseDetailsList);
return modelView;

}

}
