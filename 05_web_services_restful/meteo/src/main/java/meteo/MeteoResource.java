package meteo;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("ville")
public class MeteoResource {
	
	@PUT
	@Path("{ville}")
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	public Response merge(@PathParam("ville") String ville, Meteo meteo) {
		return Response.status(201).entity(meteo).build();
	}

}
