package com.nttdata.web.utils;

public class CrestaQueryConstants {

	public static final String ACTUAL_LABEL_DEFECT_lEAKAGE = "Actual Defect Leakage for  ";

	public static final int STEP_SIZE = 5;
	public static final String NOT_APPLICABLE = "NA";
	public static final String RELEASE = "Release";
	public static final String FUNCTIONAL_DEFECT = "Functional Defects";
	public static final String DEFECT_DENSITY = "Defect Density";
	public static final String DEFECT_ACCEPTANCE = "Defect Acceptance";
	public static final String DEFECT_DEFERRAL = "Defect Deferral";
	public static final String ALL_DEFECTS = "All Defects";
	public static final String DEFECT_LEAKAGE = "Defect Leakage";
	public static final String DEFECT_REJECTION = "Defect Rejection";

	public static final String PREDICTION_LABEL_DEFECT_LEAKAGE = "Predicted Defect Leakage for  ";

	public static final String ACTUAL_LABEL_DEFECT_DENSITY = "Actual Defect Density for  ";

	public static final String PREDICTION_LABEL_DEFECT_DENSITY = "Predicted Defect Density for  ";

	public static final String ACTUAL_LABEL_DEFECT_ACCEPTANCE = "Actual Defect Acceptance Rate for  ";

	public static final String ACTUAL_LABEL_DEFECT_DEFERRAL_RATE = "Actual Defect Deferral Rate for  ";

	public static final String PREDICTION_LABEL_DEFECT_ACCEPTANCE = "Predicted Defect Acceptance Rate for  ";

	public static final String PREDICTION_LABEL_DEFECT_DEFERRAL = "Predicted Defect Deferral Rate for  ";

	public static final String QRY_GET_DEFECT_ACCEPTANCE_DATA = "select release_version, acceptance from usecase1A where userid=? and pred_code='DEFECT_ACCEPTANCE_RATE'";

	public static final String ACCURACY_LABEL = " Accuracy : ";

	public static final String QRY_GET_CREDENTIAL_DETAILS = "select * from user_master where BINARY user_name = ? and BINARY password = ?";

	public static final String QRY_GET_CONFIG_LIST = "SELECT pm.pred_description, mm.m_name, pmd.weightage, mm.m_code FROM pred_metrics_mapping p "
			+ " join predict_mst pm on p.pred_id=pm.id " + " join metrics_mst mm on p.metrics_id=mm.id"
			+ " join predict_metrics_details pmd on pmd.pred_metrics_mapping_id = p.id " + " where pm.pred_code = ?";

	public static final String QRY_GET_PREDICT_LIST = "select * from predict_mst order by sequence";

	public static final String QRY_GET_PROJECT_LIST = "select p.id, p.redmine_proj_id,p.proj_name, p.proj_description "
			+ "from project_mst p " + "inner join user_projects_mapping upm on upm.projectid = p.id "
			+ "inner join user_mst um on upm.userid= um.id where upm.userid=?";

	public static final String QRY_GET_METRICS_LIST = "SELECT pmst.pred_code,pmm.id,pmm.metrics_id,pmm.job_name,m.metrics,pmd.ucl,pmd.lcl,pmd.weightage "
			+ "from pred_metrics_mapping pmm,metrics_master m, predict_metrics_details pmd, predict_mst pmst where "
			+ "pmm.metrics_id=m.id and pmm.id=pmd.pred_metrics_mapping_id and pmst.id = pmm.pred_id";

//	public static final String HOST = JubatusUtils.readPropertiesFile("host");
	
	public static final String HOST = JubatusUtils.readPropertiesFile("jubatus_server_host_ip");

	public static final int PORT = Integer.parseInt(JubatusUtils.readPropertiesFile("port"));

	public static final String NAME = "trivial_stat";

	public static final int CLIENT_TIMEOUT = 500;

	public static final int PRED_PORT_DD = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_dd"));
	public static final int PRED_PORT_DF = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_df"));
	public static final int PRED_PORT_AD = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_ad"));
	public static final int PRED_PORT_FD = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_fd"));
	public static final String PRED_NAME = "regression";

	public static final int PRED_PORT_DR = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_dr"));

	public static final int PRED_PORT_DL = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_dl"));

	public static final int PRED_PORT_DA = Integer.parseInt(JubatusUtils.readPropertiesFile("pred_port_da"));

	public static final int N_PERCENT = 10;

	public static final String QRY_GET_DEFECT_DATA = "select * from usecase1 where userid=? and pred_code = ?";

	public static final String QRY_GET_DEFECT_DEFERRAL_DATA = "select * from usecase1B where userid=? and pred_code = ?";

	public static final String QRY_GET_RELEASE_DATA = "select release_version from usecase1 where userid=? and pred_code = ?";

	public static final String QRY_GET_USECASE3_RELEASE_DATA = "select release_version from usecase3 where userid=? and pred_code = ?";

	public static final String QRY_GET_LEAKAGE_DATA = "select leakage from usecase3 where userid=? and pred_code = ?";

	public static final String QRY_GET_DEFECT_DENSITY_DATA = "select release_version, density, leakage, rejection from usecase1 where userid=? and pred_code='DEFECT_DENSITY'";

	public static final String QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1 = "select * from usecase1 where userid=? and pred_code='DEFECT_DENSITY'";

	// Get last ten Predictions

	public static final String QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_DENSITY = "select * from usecase1_lastprediction";

	public static final String QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_ACCEPTANCE = "select * from usecase1A_lastprediction";

	public static final String QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_DEFERRAL = "select * from usecase1B_lastprediction";

	public static final String QRY_GET_LAST_TEN_PREDICTIONS_DEFECT_COUNT = "select * from usecase1C_lastprediction";

	public static final String QRY_GET_LAST_TEN_PREDICTIONS_FUNCTIONAL_DEFECT_COUNT = "select * from usecase1D_lastprediction";

	// Last Ten Prediction Ends

	public static final String QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1_TELEPHONICA = "select * from usecase1Telephonica where user_id=? and pred_code='DEFECT_DENSITY'";

	public static final String QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1A_TELEPHONICA = "select * from usecase1ATelephonica where user_id=? and pred_code2='DEFECT_ACCEPTANCE_RATE'";

	public static final String QRY_GET_DEFECT_DENSITY_DATA_FOR_USECASE1B_TELEPHONICA = "select * from usecase1BTelephonica where user_id=? and pred_code1='DEFECT_DEFERRAL_RATE'";

	public static final String QRY_GET_DEFECT_COUNT_DATA_FOR_USECASE1C_TELEPHONICA = "select * from usecase1CTelephonica where user_id=? and pred_code3='DEFECT_COUNT'";

	public static final String QRY_GET_FUNCTIONAL_DEFECT_COUNT_DATA_FOR_USECASE1D_TELEPHONICA = "select * from usecase1DTelephonica where user_id=? and tpr_name='Functional'";

	public static final String QRY_GET_ALL_DATA_FOR_USECASE1D_TELEPHONICA = "select * from usecase1DTelephonica where user_id=?";
	// Telephonica Delete Queries

	public static final String QRY_USECASE1_TELEPHONICA_DELETE_DATA = "delete from usecase1Telephonica where user_id = ? and pred_code = ?";

	public static final String QRY_USECASE1A_TELEPHONICA_DELETE_DATA = "delete from usecase1ATelephonica where user_id = ? and pred_code2 = ?";

	public static final String QRY_USECASE1B_TELEPHONICA_DELETE_DATA = "delete from usecase1BTelephonica where user_id = ? and pred_code1 = ?";

	public static final String QRY_USECASE1C_TELEPHONICA_DELETE_DATA = "delete from usecase1CTelephonica where user_id = ? and pred_code3 = ?";

	public static final String QRY_USECASE1D_TELEPHONICA_DELETE_DATA = "delete from usecase1DTelephonica where user_id = ? and pred_code = ?";

	// Telephonica Delete Queries End

	public static final String QRY_USECASE1_DELETE_DATA = "delete from usecase1 where userid = ? and pred_code = ?";

	public static final String QRY_USECASE2_DELETE_DATA = "delete from usecase2 where userid = ? and pred_code = ?";

	public static final String QRY_USECASE3_DELETE_DATA = "delete from usecase3 where userid = ? and pred_code = ?";

	public static final String QRY_USECASE1A_DELETE_DATA = "delete from usecase1A where userid = ? and pred_code = ?";

	public static final String QRY_USECASE1B_DELETE_DATA = "delete from usecase1B where userid = ? and pred_code = ?";

	public static final String QRY_USECASE1C_DELETE_DATA = "delete from usecase1C where userid = ? and pred_code = ?";

	public static final String QRY_GET_MODULE_DETAILS = "select * from usecase2 where module_name = ? and pred_code=?";

	public static final String QRY_GET_MODULE_LIST = "select module_name,release_version,density,leakage,rejection from usecase2 where userid=? and pred_code =?";

	public static final String QRY_GET_METRICS_ID = "select id from pred_metrics_mapping where metrics_id=? and pred_id=?";

	public static final String QRY_UPDATE_DATA = "update predict_metrics_details set ucl=?,lcl=?,seconducl=?,secondlcl=?,weightage=? where pred_metrics_mapping_id=?";

	public static final String QRY_GET_DEFECT_LEAKAGE_DATA = "select release_version, density, leakage, rejection from usecase3 where userid=? and pred_code= ?";

	public static final String QRY_GET_AVG_DATA = "select max(avgitems.release_version)+1 as releaseVersionMAX, avg(avgitems.density) as densityAVG, avg(avgitems.leakage) as leakageAVG, avg(avgitems.rejection) as rejectionAVG"
			+ " from (select * from cresta.usecase2 d where userid=? and pred_code = ? and module_name=?"
			+ " order by id desc limit ?) as avgitems";
	public static final String QRY_GET_USECASE3_AVG_DATA = "select max(avgitems.release_version)+1 as releaseVersionMAX, avg(avgitems.density) as densityAVG, avg(avgitems.leakage) as leakageAVG, avg(avgitems.rejection) as rejectionAVG"
			+ " from (select * from cresta.usecase3 d where userid=? and pred_code = ? "
			+ " order by id desc limit ?) as avgitems";
	public static final String QRY_GET_USECASE1A_AVG_DATA = "select max(avgitems.release_version)+1 as releaseVersionMAX, avg(avgitems.density) as densityAVG, avg(avgitems.leakage) as leakageAVG, avg(avgitems.acceptance) as acceptanceAVG"
			+ " from (select * from cresta.usecase1A d where userid=? and pred_code = ? "
			+ " order by id desc limit ?) as avgitems";

	public static final String QRY_GET_AVG_DATA_DEFECT_DENSITY = "select max(avgitems.release_version)+1 as releaseVersionMAX, avg(avgitems.density) as densityAVG, avg(avgitems.leakage) as leakageAVG, avg(avgitems.rejection) as rejectionAVG"
			+ " from (select * from cresta.usecase1 d where userid=? and pred_code = ? "
			+ " order by id desc limit 1,?) as avgitems";

	public static final String ACTUAL_LABEL_FUNCTIONAL_DEFECT = "Actual Functional Defect for  ";

	public static final String PREDICTION_LABEL_FUNCTIONAL_DEFECT = "Predicted Functional Defect for  ";

	public static final String QRY_GET_FUNCTIONAL_DEFECT_COUNT = "select * from usecase1D where userid=? and pred_code='FUNCTIONAL_DEFECT_COUNT'";

	public static final String QRY_GET_ACCEPTANCE = "select * from usecase1A where userid=? and pred_code='DEFECT_ACCEPTANCE_RATE'";

	public static final String QRY_USECASE1D_DELETE_DATA = "delete from usecase1D where userid = ? and pred_code = ?";

	public static final String PREDICTION_LABEL_DEFECT_COUNT = "Predicted Defect Count for  ";

	public static final String ACTUAL_LABEL_DEFECT_COUNT = "Actual Defect Count for  ";

	public static final String QRY_GET_DEFECT_COUNT_DATA = "select * from usecase1C where userid=? and pred_code='DEFECT_COUNT'";

	public static final String QRY_GET_ALGORITHM_LIST = "select * from pred_algorithm";

	public static final String QRY_GET_TELEPHONICA_DATA = "select bg_bug_id,bg_status,bg_priority,bg_user_02 from td.bug where bg_status='Closed' and (bg_priority='P1' or bg_priority='P2')";

	public static final String QRY_GET_PIECHART_DATA = "select COUNT(td.BUG.BG_BUG_ID) as No_of_Bugs,td.BUG.BG_USER_02 from td.BUG  where bg_status='Closed' and (bg_priority='P1' or bg_priority='P2') GROUP BY td.BUG.BG_USER_02";

	public static final String QRY_GET_PROJECT_WISE_TEST_ENVIRONMENT_DEFECTS = "select COUNT(td.BUG.BG_BUG_ID) as No_of_Bugs,td.BUG.BG_PROJECT from td.BUG  where bg_status='Closed' and (bg_priority='P1' or bg_priority='P2') and BG_USER_02=? GROUP BY BG_PROJECT";

	public static final String LBL_PROJECT = "Projects";

	public static final String HISTORICAL_DATA_FILE_PATH = "/opt/apache-tomcat-8.0.36/webapps/resources1/usecase4/train_data.csv";

	public static final String RECENT_DATA_FILE_PATH = "/opt/apache-tomcat-8.0.36/webapps/resources1/usecase4/test_data.csv";

	public static final String PROPERTIES_FILE_PATH = "/opt/apache-tomcat-8.0.36/webapps/resources1/config.properties";
	
	//public static final String QRY_GET_DEFECT_COUNT_DATA_BoA = "SELECT DefectCount,DATE_FORMAT(DetectedDate, '%b-%y') AS DetectedDate,id FROM crestaBoADefects c"; 
	
	public static final String QRY_GET_DEFECT_COUNT_DATA_BoA = "SELECT * FROM crestaBoADefects"; 

	
	public static final String CUMULATIVE_DEFECTS = "Cumulative Defects";
	
	public static final String MONTH_WISE_DEFECTS = "Months";

}
