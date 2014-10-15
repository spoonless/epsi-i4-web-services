package epsi.battle.net;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Exemple d'utilisation du servide REST World of Warcraft
 * (http://blizzard.github.io/api-wow-docs/)
 */
public class RestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());
		
		// TODO à implémenter
	}
}
