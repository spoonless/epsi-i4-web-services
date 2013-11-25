package fr.epsi.i4.bookmark.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class BookmarkApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(QrCodeBodyWriter.class);
		classes.add(BookmarksResource.class);
		return classes;
	}

}
