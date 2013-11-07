package fr.epsi.i4.bookmark;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class BookmarkRepository {

	private final Map<String, Bookmark> map = new HashMap<>();
	private String latestId;

	public synchronized void add(String id, Bookmark bookmark) throws InvalidBookmarkException {
		bookmark.validate();
		map.put(id, bookmark);
		latestId = id;
	}

	public synchronized String getLatestId() {
		return latestId;
	}

	public synchronized Bookmark get(String id) {
		return map.get(id);
	}

	public synchronized void delete(String id) {
		map.remove(id);
	}

	public synchronized Set<String> getIds() {
		return map.keySet();
	}

	@PostConstruct
	public void populate() throws InvalidBookmarkException {
		add(UUID.randomUUID().toString(), new Bookmark("restcookbook", "quelques articles intéressants sur REST", "http://restcookbook.com/"));
		add(UUID.randomUUID().toString(), new Bookmark("tomee", "Le site officiel de tomee", "http://tomee.apache.org"));
		add(UUID.randomUUID().toString(), new Bookmark("API REST WoW", "un exemple d'API rest", "http://blizzard.github.io/api-wow-docs/"));
	}

}
