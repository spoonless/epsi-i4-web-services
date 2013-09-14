package epsi;

public class Main {

	public static void main(String[] args) {

		Student student = new Student();
		student.setName("John Doe");
		student.setAge(21);
		student.setAdress(new Adress("1 cours de la Martinique", "33000", "Bodeaux"));

		System.out.println(student);

	}

}
