package fr.epsi.i4.bookmark.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookmarks")
public class Bookmarks {

	private final List<Link> links = new ArrayList<>();
	private final List<Link> navigationLinks = new ArrayList<>();

	@XmlElement(name = "link")
	public List<Link> getLinks() {
		return links;
	}
	
	@XmlElementWrapper(name="nav")
	@XmlElement(name = "link")
	public List<Link> getNavigationLinks() {
		return navigationLinks;
	}

	public void addNavigationLink(Link link) {
		navigationLinks.add(link);
	}

	public void addBookmarkLink(URI uri) {
		links.add(new Link(uri, "bookmark"));
	}

}
