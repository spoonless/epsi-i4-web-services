package epsi.battle.net;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Exemple d'utilisation de l'API Web de World of Warcraft
 * (http://blizzard.github.io/api-wow-docs/)
 */
public class HttpClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());

		WebTarget target = client.target("http://eu.battle.net");
		Races races = target.path("/api/wow/data/character/races").queryParam("locale", "fr_FR").request().get(Races.class);
		System.out.println(races.getRaces());
	}
}
