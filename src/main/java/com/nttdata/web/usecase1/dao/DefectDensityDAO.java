package com.nttdata.web.usecase1.dao;

import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1.model.DefectDensityModelTelephonica;

public interface DefectDensityDAO {

	List<DefectDensityModelTelephonica> getDefectDensityAndReleaseDataTelephonica(String userId, String predictionId);

	List<ReleaseDetails> getLastTenPredictions(int algorithmId);
}
