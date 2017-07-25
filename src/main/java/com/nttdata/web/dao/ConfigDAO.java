package com.nttdata.web.dao;

import java.util.List;
import java.util.Map;

import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;
import com.nttdata.web.usecase1C.model.DefectCountModel;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

public interface ConfigDAO {
	public List<PredictionBean> getPredictionListForUser();

	public List<MetricsBean> getPredictMetricsMapping();

	List<Integer> getReleaseDataForUseCase3(String userId, String predictionCode);

	List<DefectLeakageModel> getDefectDensityData(int userid);

	public List<DefectDeferralModel> getDefectDeferralData(int userid, String pred_code);

	public Map<String, List<ModuleDetails>> getModuleWiseData(int userid, String predictionCode);

	public Map<String, List<Integer>> getModuleDetails(String moduleName, String predictionCode);

	public void saveData(MetricsBean metricsBean);

	public List<DefectLeakageModel> getDefectLeakageData(int userid, String predictionCode);

	public List<ModuleDetails> getAvgData(int userId, String predictionCode, String moduleName, int limit);

	public List<ModuleDetails> getAvgDataForDefectDesnity(int userId, String predictionCode, int limit);

	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit);

	public List<ModuleDetails> getAvgDataForDefectDeferral(int userId, String predictionCode, int limit);

	public List<Integer> getDefectDeferralCount(int userid, String predictionCode);

	public List<FunctionalDefectCountModel> getFunctionalDefectData(int userid);

	public List<Integer> getDefectAcceptanceCountTelephonica(int userid, String predictionCode);

	public List<Integer> getDefectDensityCountTelephonica(int userid, String predictionCode);

	public List<Integer> getDefectCountTelephonica(int userid, String predictionCode);

	public List<DefectCountModel> getDefectCountData(int userid);

	public List<DefectDensityModelTelephonica> getDefectDensityDataForUseCase1Telephonica(int userid);

	public List<Integer> getFunctionalDefectDensityCountTelephonica(int userid, String predictionCode);

	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceDataTelephonica(int userid);

	public List<Integer> getDefectModuleCount(int userid, String predictionCode, Integer metricsId);

	List<DefectDeferralModelTelephonica> getDefectDeferralTelephonicaData(int userid, String pred_code);
}
