package net.atos.mobile.roadshow.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import javax.ws.rs.core.MediaType;

import net.atos.mobile.roadshow.db.MongoClient;

@Path("/feedback")
public class FeedbackService {

	private static final String COLLECTION_NAME = "feedbacks";

	private static Logger log = Logger.getLogger(FeedbackService.class
			.getSimpleName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listFeedback() {

		log.debug("listFeedback()");
		DB db = MongoClient.getDB();
		DBCursor results = null;

		try {
			db.requestStart();
			results = db.getCollection(COLLECTION_NAME).find(
					BasicDBObjectBuilder.start().get());
		
		} finally {
			db.requestDone();
		}

		return Response.ok(results.toArray().toString()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveFeedback(String feedback) {

		log.debug("saveFeedback " + feedback);

		DB db = MongoClient.getDB();

		try {

			db.requestStart();

			DBObject dbObject = (DBObject) JSON
					.parse(feedback); // Check that this works

			DBCollection feedbackColl = db.getCollection(COLLECTION_NAME);

			feedbackColl.insert(dbObject);

		} finally {
			db.requestDone();
		}

		return Response.ok().build();
	}

}
