package epsi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Student {

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int age;

	@Getter
	@Setter
	private Adress adress;

}
