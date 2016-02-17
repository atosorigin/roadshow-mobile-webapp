package net.atos.mobile.roadshow.db;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.Mongo;

import net.atos.mobile.roadshow.util.Configuration;

/**
 * Singleton instance of Mongo connection pool. 
 * Initialised as ServletContextListener
 * @author a171581
 *
 */
public class MongoClient {

	private static Logger log = Logger.getLogger(MongoClient.class);

	private static Mongo mongo;
	
	private static DB mongoDB;
	
	private static boolean local;
	
	private static boolean authenticated;
	
	private static String username;
	private static String password;
	private static String db;
	private static String host;
	private static Integer port;


	private MongoClient() {
	}

	/**
	 * Connect to mongo instance using env variables if present
	 * or fall back to local instance
	 * @return
	 * @throws UnknownHostException
	 */
	public static synchronized Mongo connect() {

		if (mongo == null) {

			log.debug("Creating new MongoDB connection");

			host = System.getenv(Configuration.ENV_MONGO_HOST_OPENSHIFT);
			
			if (host == null || "".equals(host)) {
				log.info("Not running on OpenShift. Falling back to local mongodb");
				local = true;
				host = "database";

				String mongoport = "27017";							
				port = Integer.decode(mongoport);
				
				username = System.getenv(Configuration.ENV_MONGO_USER_LOCAL);
				password = System.getenv(Configuration.ENV_MONGO_PWD_LOCAL);
				db = "roadshow";
				
			} else {

				log.debug("Retrieved host from OpenShift");

				username = System.getenv(Configuration.ENV_MONGO_USER_OPENSHIFT);
				password = System.getenv(Configuration.ENV_MONGO_PWD_OPENSHIFT);				
				db = System.getenv(Configuration.ENV_MONGO_DB_OPENSHIFT);
				
				
				String mongoport = System.getenv("OPENSHIFT_MONGODB_DB_PORT");				
				port = Integer.decode(mongoport);
			}

			try {
				mongo = new Mongo(host, port);
			} catch (UnknownHostException ex) {
				throw new RuntimeException("Failed to connect to db", ex);
			}
		}
		
		return mongo;
	}
	
	/**
	 * Get hold of the database instance
	 * @return
	 */
	public static synchronized DB getDB() {
		
		if (mongoDB == null) {
			
			if (mongo == null) {
				connect();
			}
			
			mongoDB = mongo.getDB(db);
		}
		
		if (local) {
		
			if (db == null || "".equals(db)) {
				log.debug("No database found in environment variables, assuming \"roadshow\"");
				db = "roadshow";
			}
		}
		
		log.debug("Retrieving database [" + db + "]");
		DB mongoDB = mongo.getDB(db);
		
		//only authenticate if we're not already authenticated and not running locally (local auth OFF)
		if (!authenticated && !local) {
			
			log.debug("Not authenticated against database and not running locally. Authenticating now.");
			authenticated = mongoDB.authenticate(username, password.toCharArray());
			
			if (!authenticated) {
				throw new RuntimeException("Failed to authenticate against db");
			}
		}
		
		return mongoDB;
	}
	
	/**
	 * Shutdown the mongo instance
	 */
	public static void disconnect() {
		
		log.info("Disconnecting mongo client");
		if (mongo != null) {
			mongo.close();
		}
	}
}
