package com.nttdata.web.usecase1.dao;


import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1.model.DefectDensityModel;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;

public interface DefectDensityDAO {
	List<DefectDensityModel> getDefectDensityAndReleaseData(String userId);

	List<DefectDensityModelTelephonica> getDefectDensityAndReleaseDataTelephonica(String userId);

	List<ReleaseDetails> getLastTenPredictions(int algorithmId);
}
