package net.atos.mobile.roadshow.util;

public class Configuration {

	public static final String ENV_MONGO_USER_LOCAL = "LOCAL_MONGODB_DB_USERNAME";
	public static final String ENV_MONGO_PWD_LOCAL = "LOCAL_MONGODB_DB_PWD";
	public static final String ENV_MONGO_DB_LOCAL = "LOCAL_MONGODB_DB_DB";
	
	public static final String ENV_MONGO_USER_OPENSHIFT = "OPENSHIFT_MONGODB_DB_USERNAME";
	public static final String ENV_MONGO_PWD_OPENSHIFT = "OPENSHIFT_MONGODB_DB_PASSWORD";
	public static final String ENV_MONGO_DB_OPENSHIFT = "OPENSHIFT_APP_NAME";
	public static final String ENV_MONGO_HOST_OPENSHIFT = "OPENSHIFT_MONGODB_DB_HOST";
	
	public static final String ENV_DATA_DIR_OPENSHIFT = "OPENSHIFT_DATA_DIR";
	public static final String ENV_DATA_DIR_LOCAL = "LOCAL_DATA_DIR";
	public static final String ATT_DATA_DIR = "resourcePathUri";
	
}
