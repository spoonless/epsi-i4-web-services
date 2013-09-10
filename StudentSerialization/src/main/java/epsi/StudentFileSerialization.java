package epsi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentFileSerialization {

	public static void main(String[] args) throws Exception {

		try (FileOutputStream fos = new FileOutputStream("student.serialized");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			Student student = new Student("John Doe", 24);
			oos.writeObject(student);
		}

		try (FileInputStream fis = new FileInputStream("student.serialized");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			Student student = Student.class.cast(ois.readObject());
			System.out.println(student.toString());
		}
	}
}
