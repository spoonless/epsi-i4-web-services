package epsi;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherServer implements Runnable {

	private static Logger LOGGER = Logger.getLogger("TeacherServer");

	@Override
	public void run() {
		ExecutorService executor = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(8888)) {
			LOGGER.info("Server started on port " + serverSocket.getLocalPort());
			while (true) {
				Socket socket = serverSocket.accept();
				executor.execute(new ServantRunnable(socket));
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot start the server", e);
		} finally {
			executor.shutdownNow();
		}
	}

	public static void main(String[] args) {
		new TeacherServer().run();
	}

	private static class ServantRunnable implements Runnable {
		private final Socket socket;

		private ServantRunnable(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			InetAddress inetAddress = socket.getInetAddress();
			try (OutputStream os = socket.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
				oos.writeObject(new Student("David Gayerie", 39));
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "From " + inetAddress, e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "From " + inetAddress, e);
				}
			}
		}
	}
}
