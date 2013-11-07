package fr.epsi.i4.bookmark.web;

import java.net.URI;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import fr.epsi.i4.bookmark.Bookmark;
import fr.epsi.i4.bookmark.BookmarkRepository;
import fr.epsi.i4.bookmark.InvalidBookmarkException;

@Path("bookmarks")
public class BookmarkResource {

	@EJB
	private BookmarkRepository bookmarkRepository;

	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(Bookmark bookmark) {
		String id = UUID.randomUUID().toString();
		merge(id, bookmark);
		URI resourceLocation = getUriBuilderFromId(id).build();
		return Response.created(resourceLocation).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(@FormParam("name") String name, @FormParam("description") String description, @FormParam("url") String url) {
		Bookmark bookmark = new Bookmark(name, description, url);
		return add(bookmark);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void merge(@PathParam("id") String id, Bookmark bookmark) {
		try {
			bookmarkRepository.add(id, bookmark);
		} catch (InvalidBookmarkException e) {
			Response response = Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public BookmarkResponse get(@PathParam("id") String id) {
		Bookmark bookmark = bookmarkRepository.get(id);
		if (bookmark == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return new BookmarkResponse(bookmark, getUriBuilderFromId(id));
	}

	@GET
	@Path("latest")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getLatest() {
		String latestId = bookmarkRepository.getLatestId();
		if (latestId == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		BookmarkResponse bookmarkResponse = get(latestId);
		URI contentLocationUri = getUriBuilderFromId(latestId).build();
		return Response.ok().header("Content-location", contentLocationUri.toASCIIString()).entity(bookmarkResponse).build();
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") String id) {
		bookmarkRepository.delete(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Bookmarks getAll() {
		Bookmarks bookmarks = new Bookmarks();

		for (String id : bookmarkRepository.getIds()) {
			URI uri = getUriBuilderFromId(id).build();
			bookmarks.addLink(uri);
		}

		return bookmarks;
	}

	@GET
	@Path("{id}/qrcode")
	@Produces("image/png")
	public String getQrCode(@PathParam("id") String id) {
		return get(id).getUrl();
	}

	private UriBuilder getUriBuilderFromId(String id) {
		return uriInfo.getBaseUriBuilder().path(this.getClass()).path(id);
	}

}
