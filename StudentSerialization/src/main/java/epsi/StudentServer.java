package epsi;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentServer {

	private static Logger LOGGER = Logger.getLogger("StudentServer");

	public static void main(String[] args) throws IOException {

		ExecutorService executor = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(9999)) {
			LOGGER.info("Server started on port " + serverSocket.getLocalPort());
			while (true) {
				Socket socket = serverSocket.accept();
				executor.execute(new ServantRunnable(socket));
			}
		} finally {
			executor.shutdownNow();
		}
	}

	private static class ServantRunnable implements Runnable {
		private final Socket socket;

		private ServantRunnable(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			InetAddress inetAddress = socket.getInetAddress();
			try (InputStream is = socket.getInputStream(); ObjectInputStream ois = new ObjectInputStream(is)) {
				Student s = Student.class.cast(ois.readObject());
				LOGGER.info("From " + inetAddress + "\n> " + s);
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
