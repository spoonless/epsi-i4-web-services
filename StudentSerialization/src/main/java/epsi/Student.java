package epsi;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;
	private final int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Hello, my name is " + name +
				" and I am " + age + " years old.";
	}
}
