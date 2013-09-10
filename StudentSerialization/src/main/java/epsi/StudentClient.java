package epsi;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class StudentClient {

	public static void main(String[] args) throws Exception {

		Student student = new Student("David Gayerie", 39);

		try (Socket socket = new Socket("localhost", 9999);
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os)) {
			oos.writeObject(student);
		}

	}
}
