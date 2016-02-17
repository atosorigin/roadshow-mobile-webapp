package net.atos.mobile.roadshow.ws;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import net.atos.mobile.roadshow.db.MongoClient;

public class ReferenceDataService {
	
	private static final String LOCATION_COLLECTION_NAME = "locations";
	
	private static final String STATUS_COLLECTION_NAME = "statuses";

	private static Logger log = Logger.getLogger(ReferenceDataService.class);
	
	@Path("/locations")
	public Response listLocations() {
		log.debug("listLocations");
		return Response.ok(getReferenceData(LOCATION_COLLECTION_NAME)).build();
	}
	
	@Path("/statuses")
	public Response listStatuses() {
		log.debug("listStatuses");
		return Response.ok(getReferenceData(STATUS_COLLECTION_NAME)).build();
	}
	
	private String getReferenceData(final String collectionName) {
		DB db = MongoClient.getDB();
		DBCursor results = null;

		try {
			db.requestStart();
			
			DBObject allQuery = new BasicDBObject();
			DBObject removeIdProjection = new BasicDBObject("_id", 0);
			
			results = db.getCollection(collectionName).find(allQuery, 
					removeIdProjection);
			
		} finally {
			db.requestDone();
		}

		return results.toArray().toString();
		
	}

}
