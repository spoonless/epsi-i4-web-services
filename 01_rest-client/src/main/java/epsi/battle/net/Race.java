package epsi.battle.net;

public class Race {

	private Long id;
	private WowSide side;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WowSide getSide() {
		return side;
	}

	public void setSide(WowSide side) {
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
