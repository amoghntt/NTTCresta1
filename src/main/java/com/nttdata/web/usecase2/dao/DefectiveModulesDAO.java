package com.nttdata.web.usecase2.dao;


import java.util.List;
import java.util.Map;

import com.nttdata.web.usecase3.model.DefectLeakageModel;

public interface DefectiveModulesDAO {
	Map<String, List<Integer>> getModuleData(String predictionCode, String moduleName);
}
