package com.nttdata.web.utils;

public class TestJubatusUtils {

	public static void main(String args[]) {
		//int jubaClassifierPort = 19199;
		int jubaRegressionForDefectDensity = 9197;
		int jubaRegressionForDefectLeakage = 9198;
		int jubaRegressionForDefectRejection = 9199;

		/*boolean jubaClasssifierStarted = JubatusUtils.startJubatus(JubatusUtils.JUBACLASSIFIER,
				"/home/cresta/jubatus-example-master/gender/gender.json", jubaClassifierPort);*/
		
		boolean jubaRegressionStartedForDefectDensity = JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
				"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", jubaRegressionForDefectDensity);
		boolean jubaRegressionStartedForDefectLeakage = JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
				"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", jubaRegressionForDefectLeakage);
		boolean jubaRegressionStartedForDefectRejection = JubatusUtils.startJubatus(CrestaConstants.JUBAREGRESSION,
				"/home/cresta/cresta_jubatus/jubatus_server/regression/config.json", jubaRegressionForDefectRejection);
		JubatusUtils.stopJubatus(jubaRegressionForDefectDensity);
		JubatusUtils.stopJubatus(jubaRegressionForDefectLeakage);
		JubatusUtils.stopJubatus(jubaRegressionForDefectRejection);

		if (jubaRegressionStartedForDefectDensity) {
			/*try {
				//Thread.sleep(50000);
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			JubatusUtils.stopJubatus(jubaRegressionForDefectDensity);*/
			
		}
		
		if (jubaRegressionStartedForDefectLeakage) {
			/*try {
				Thread.sleep(50000);
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			JubatusUtils.stopJubatus(jubaRegressionForDefectLeakage);*/
			
		}
		
		if (jubaRegressionStartedForDefectRejection) {
		/*	try {
				Thread.sleep(50000);
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			JubatusUtils.stopJubatus(jubaRegressionForDefectRejection);*/
			
		}
		else
		{
			//JubatusUtils.stopJubatus(jubaRegressionForDefectRejection);
			
		}
	}
}
