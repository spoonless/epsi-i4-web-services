package fr.epsi.i4.bookmark;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.glassfish.jersey.simple.SimpleContainerProvider;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import fr.epsi.i4.bookmark.web.BookmarkApplication;

public class Main implements Container {

	private Container container;
	
	public Main() {
		SimpleContainerProvider provider = new SimpleContainerProvider();
		container = provider.createContainer(Container.class, new BookmarkApplication());
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = new ContainerServer(new Main());

		try(Connection connection = new SocketConnection(server)) {
			SocketAddress address = new InetSocketAddress(8080);
			connection.connect(address);
			synchronized (connection) {
				connection.wait();
			}
		}
	}

	@Override
	public void handle(Request request, Response response) {
		if (request.getPath().getPath().equals("/")) {
			if (request.getMethod().equals("GET")) {
				response.setContentType("text/html");
				try(PrintStream printStream = response.getPrintStream()) {
					printStream.println("Hello");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				response.setCode(405);
				try {
					response.close();
				} catch (IOException e) {
				}
			}
		}
		else {
			container.handle(request, response);
		}
		
	}
}
