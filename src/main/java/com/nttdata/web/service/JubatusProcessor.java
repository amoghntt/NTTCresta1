package com.nttdata.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.jubatus.helper.JubatusHelper;
import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictedModel;
import com.nttdata.web.usecase1.service.FindDefectsServiceUseCase1;
import com.nttdata.web.usecase1A.service.FindDefectsServiceUseCase1A;
import com.nttdata.web.usecase1B.service.FindDefectsServiceUseCase1B;
import com.nttdata.web.usecase1C.service.FindDefectsServiceUseCase1C;
import com.nttdata.web.usecase1D.service.FindDefectsServiceUseCase1D;
import com.nttdata.web.usecase2.service.FindDefectsServiceUseCase2;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
import com.nttdata.web.usecase3.service.FindDefectsServiceUseCase3;

@Service
public class JubatusProcessor {

	private static final Logger log = LoggerFactory.getLogger(JubatusProcessor.class);

	@Autowired
	FindDefectsServiceUseCase1 findDefectsUseCase1;

	@Autowired
	FindDefectsServiceUseCase2 findDefectsModuleWise;

	@Autowired
	FindDefectsServiceUseCase3 findDefectsForUseCase3;

	@Autowired
	FindDefectsServiceUseCase1D findDefectsForUseCase1D;

	@Autowired
	FindDefectsServiceUseCase1A findDefectsForUseCase1A;

	@Autowired
	FindDefectsServiceUseCase1B findDefectsUseCase1B;

	@Autowired
	FindDefectsServiceUseCase1C findDefectsUseCase1C;

	public List<Integer> predictDensity(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			// resultList.add(findDefects.predictData(userId,predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public Map<String, PredictedModel> predictDensityModuleWise(int userId, String predictionCode) {
		Map<String, PredictedModel> resultList = new HashMap<String, PredictedModel>();
		try {
			resultList = findDefectsModuleWise.predictData(userId, predictionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDensityForUseCase3(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsForUseCase3.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDensityForUseCase1D(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsForUseCase1D.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDensityForUseCase1(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsUseCase1.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDensityForUseCase1A(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsForUseCase1A.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<int[]> computeUclLclData(int metricsId, List<Integer> defectCount) {
		List<int[]> resultList = new LinkedList<int[]>();
		try {
			MetricsBean metricsBean = new JubatusHelper().getCalculateLimit(metricsId, defectCount);
			int[] ucl;
			int[] lcl;

			ucl = metricsBean.getUcl();
			lcl = metricsBean.getLcl();
			resultList.add(lcl);
			resultList.add(ucl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<int[]> computeUclLclData1(int metricsId, List<DefectLeakageModel> defectCount) {
		List<int[]> resultList = new LinkedList<int[]>();
		try {
			MetricsBean metricsBean = new JubatusHelper().getCalculateLimit1(metricsId, defectCount);
			int[] ucl;
			int[] lcl;

			ucl = metricsBean.getUcl();
			lcl = metricsBean.getLcl();
			resultList.add(lcl);
			resultList.add(ucl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDefectDeferralRate(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsUseCase1B.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> predictDefectCountRate(int userId, String predictionCode) {
		List<Integer> resultList = new ArrayList<Integer>();
		try {
			resultList.add(findDefectsUseCase1C.predictData(userId, predictionCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Integer> getAllPredictionResultUseCase1D(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1D_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1D_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);

		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1D_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}

	public List<Integer> getAllPredictionResultUseCase1(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);

		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}
	
	public List<Integer> getAllPredictionResultTelephonicaUseCase1(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);

		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}

	public List<Integer> getAllPredictionResultUseCase1C(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);

		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}

	public List<Integer> getAllPredictionResultUseCase1B(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1B_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1B_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);
		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1B_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}

	public List<Integer> getAllPredictionResultUseCase1A(String predictionId, String userId, int algorithmId) {

		List<Integer> predictedResultList = new ArrayList<Integer>();

		int predictedResult1 = Java2PythonExecutor
				.getPredictionResult(ScikitConstants.UC1A_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);

		int predictedResult2 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1A_SCIKIT_SVR_LINEAR_SCRIPT,
				predictionId, userId);

		int predictedResult3 = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1A_SCIKIT_SVR_RBF_SCRIPT,
				predictionId, userId);
		predictedResultList.add(0, predictedResult1);
		predictedResultList.add(1, predictedResult2);
		predictedResultList.add(2, predictedResult3);
		return predictedResultList;
	}

	public List<Integer> getPredictionResultUseCase1A(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1A_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1A_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1A_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}

	public List<Integer> getPredictionResultUseCase1B(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1B_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1B_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1B_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}

	public List<Integer> getPredictionResultUseCase1C(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1C_TELEPHONICA_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}

	public List<Integer> getPredictionResultUseCase1D(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1D_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1D_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1D_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}

	public List<Integer> getPredictionResultUseCase1(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}
	
	public List<Integer> getPredictionResultTelephonicaUseCase1(String predictionId, String userId, int algorithmId) {
		int predictedResult = 0;
		List<Integer> predictedResultList = new ArrayList<Integer>();
		if (algorithmId == 2) {
			predictedResult = Java2PythonExecutor
					.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_LINEAR_REGRESSION_SCRIPT, predictionId, userId);
		} else if (algorithmId == 3) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_SVR_LINEAR_SCRIPT,
					predictionId, userId);
		} else if (algorithmId == 4) {
			predictedResult = Java2PythonExecutor.getPredictionResult(ScikitConstants.UC1_TELEPHONICA_SCIKIT_SVR_RBF_SCRIPT,
					predictionId, userId);

		}
		predictedResultList.add(predictedResult);
		return predictedResultList;
	}
}