package commentaire;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("commentaire")
public class CommentaireRessource {
	
	@PUT
	@Path("{id}")
	@Consumes({"application/json", "application/xml"})
	@Produces({"application/json", "application/xml"})
	public Response put(@PathParam("id") String id, Commentaire commentaire) {
		commentaire.setTimestamp(System.currentTimeMillis());
		return Response.status(201).entity(commentaire).build();
	}

}
