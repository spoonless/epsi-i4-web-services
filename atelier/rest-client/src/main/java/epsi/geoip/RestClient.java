package epsi.geoip;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Exemple d'utilisation du servide REST freegeoip (http://freegeoip.net/)
 */
public class RestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());

		Response response = client.target("http://freegeoip.net/xml/www.epsi.fr").request().buildGet().invoke();
		System.out.println(response.readEntity(IpResponse.class));
	}
}
