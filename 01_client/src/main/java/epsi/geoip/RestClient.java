package epsi.geoip;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Exemple d'utilisation du servide REST freegeoip (http://freegeoip.net/)
 */
public class RestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());

		WebTarget target = client.target("http://freegeoip.net/xml");
		IpResponse ipResponse = target.path("www.epsi.fr").request().get(IpResponse.class);
		System.out.println(ipResponse);
	}
}
