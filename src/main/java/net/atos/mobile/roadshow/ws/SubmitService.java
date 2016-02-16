package net.atos.mobile.roadshow.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/upload")
public class SubmitService {

	@GET
	@Produces("text/html")
	@Path("/test")
	public Response test() {
		return Response.ok("<h1>Test worked!</h1>").build();
	}
}
