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
	public static final String JUBATUS_SERVER_OS_USERNAME = JubatusUtils.readPropertiesFile("jubatus_server_os_username");
	
	public static final String JUBATUS_SERVER_OS_PASSWORD = JubatusUtils.readPropertiesFile("jubatus_server_os_password");
	
	public static final String JUBATUS_SERVER_HOST_IP = JubatusUtils.readPropertiesFile("jubatus_server_host_ip");
	
	public static final int JUBATUS_SERVER_JSCH_PORT = Integer.parseInt(JubatusUtils.readPropertiesFile("jubatus_server_jsch_port"));
	
	//public static final String JUBATUS_SERVER_ENVIRONMENT_FILE = "source /opt/jubatus/profile; ";
	public static final String JUBATUS_SERVER_ENVIRONMENT_FILE = "source /opt/apache-tomcat-8.0.36/webapps/resources1/profile; ";
	//JSCH constants for starting Jubatus server remotely : End config
	
	public static final String JUBACLASSIFIER = "jubaclassifier";
	
	public static final String JUBAREGRESSION = "jubaregression";
	
	public static final String JUBASTAT="jubastat";
	
	public static final String JUBASTAT_CONFIG_FILE_PATH = "/opt/apache-tomcat-8.0.36/webapps/resources1/stat/stat.json";
	
	public static final String JUBAREGRESSION_CONFIG_FILE_PATH = "/opt/apache-tomcat-8.0.36/webapps/resources1/regression/config.json";
}
