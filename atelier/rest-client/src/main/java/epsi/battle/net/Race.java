package epsi.battle.net;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
public class Race {

	public static enum Side {
		alliance, horde
	};

	private Long id;
	private Side side;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Race [id=" + id + ", side=" + side + ", name=" + name + "]";
	}

}
