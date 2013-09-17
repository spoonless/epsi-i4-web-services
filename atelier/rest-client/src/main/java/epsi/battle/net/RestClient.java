package epsi.battle.net;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Exemple d'utilisation du servide REST World of Warcraft
 * (http://blizzard.github.io/api-wow-docs/)
 */
public class RestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());

		Response response = client.target("http://eu.battle.net/api/wow/data/character/races?locale=fr_FR").request().buildGet().invoke();
		System.out.println(response.readEntity(Races.class).getRaces());
	}
}
