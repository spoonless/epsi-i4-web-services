package fr.epsi.i4.bookmark.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookmarks")
public class Bookmarks {

	private final List<Link> links = new ArrayList<>();

	@XmlElement(name = "link")
	public List<Link> getLinks() {
		return links;
	}

	public void addLink(Link link) {
		links.add(link);
	}

	public void addBookmarkLink(URI uri) {
		links.add(new Link(uri, "bookmark"));
	}

}
