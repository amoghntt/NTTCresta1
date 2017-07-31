package com.nttdata.web.usecase1B.dao;

import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1B.model.DefectDeferralModel;
import com.nttdata.web.usecase1B.model.DefectDeferralModelTelephonica;

public interface DefectDefferalDAO {
	public List<DefectDeferralModel> getDefectDefferalAndReleaseData(String userId, String pred_code);

	List<DefectDeferralModelTelephonica> getDefectDefferalAndReleaseTelephonicaData(String userid, String pred_code);

	List<ReleaseDetails> getLastTenPredictions();

}
