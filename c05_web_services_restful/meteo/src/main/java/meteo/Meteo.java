package meteo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meteo {

	private float temperature;
	private String unite;

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

}
