package com.nttdata.web.utils;



public class CrestaConstants {
	public static String FILE_PATH = "E:/outputData/";
	public static String MODEL_REGRESSION_UC1_DEFECT_DENSITY = "MODEL_REGRESSION_UC1_DEFECT_DENSITY";
	public static String MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE = "MODEL_REGRESSION_UC1A_DEFECT_ACCEPTANCE_RATE";
	public static String MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE = "MODEL_REGRESSION_UC1B_DEFECT_DEFERRAL_RATE";
	public static String MODEL_REGRESSION_UC1C_ALL_DEFECTS = "MODEL_REGRESSION_UC1C_ALL_DEFECTS";
	public static String MODEL_REGRESSION_UC1D_FUNCTIONAL_DEFECT_COUNT = "MODEL_REGRESSION_UC1D_FUNCTIONAL_DEFECT_COUNT";
	public static String MODEL_REGRESSION_UC2_DEFECT_DENSITY = "ODEL_REGRESSION_UC2_DEFECT_DENSITY";
	public static String MODEL_REGRESSION_UC2_DEFECT_LEAKAGE = "MODEL_REGRESSION_UC2_DEFECT_LEAKAGE";
	public static String MODEL_REGRESSION_UC2_DEFECT_REJECTION = "MODEL_REGRESSION_UC2_DEFECT_REJECTION";
	public static String MODEL_REGRESSION_UC3_DEFECT_LEAKAGE = "MODEL_REGRESSION_UC3_DEFECT_LEAKAGE";
	
	//JSCH constants for starting Jubatus server remotely : Start
	public static final String JUBATUS_SERVER_OS_USERNAME = "cresta";
	
	public static final String JUBATUS_SERVER_OS_PASSWORD = "nttdata@123";
	
	public static final String JUBATUS_SERVER_HOST_IP = "10.248.3.91";
	
	public static final int JUBATUS_SERVER_JSCH_PORT = 22;
	
	public static final String JUBATUS_SERVER_ENVIRONMENT_FILE = "source /opt/jubatus/profile; ";
	//JSCH constants for starting Jubatus server remotely : End config
	
	public static final String JUBACLASSIFIER = "jubaclassifier";
	public static final String JUBAREGRESSION = "jubaregression";
	
	public static final String JUBAREGRESSION_CONFIG_FILE_PATH = "/home/cresta/cresta_jubatus/jubatus_server/regression/config.json";
}
