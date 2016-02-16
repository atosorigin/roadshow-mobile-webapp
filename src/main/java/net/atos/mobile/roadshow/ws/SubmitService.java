package net.atos.mobile.roadshow.ws;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/upload")
public class SubmitService {

	@Path("/test")
	public Response test() {
		return Response.ok("Test worked!").build();
	}
}
