package epsi;

public class Servers {

	public static void main(String[] args) {

		new Thread(new StudentServer()).start();
		new Thread(new TeacherServer()).start();

	}

}
