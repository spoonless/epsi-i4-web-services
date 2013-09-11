package epsi;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TeacherClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 8888);
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is)) {
			System.out.println(Student.class.cast(ois.readObject()));
		}
	}
}
