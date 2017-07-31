package com.nttdata.web.usecase3.dao;

import java.util.List;

import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.usecase3.model.DefectLeakageModel;

public interface DefectLeakageDAO {
	
	public List<DefectLeakageModel> getDefectLeakageData(int userid,String predictionCode);
	public List<ModuleDetails> getAvgDataForUseCase3(int userId, String predictionCode, int limit);

}
