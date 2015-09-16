package epsi.geoip;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class IpResponse {

	private String ip;
	private String courtryName;
	private Double latitude;
	private Double longitude;

	@XmlElement(name = "Ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@XmlElement(name = "CountryName")
	public String getCourtryName() {
		return courtryName;
	}

	public void setCourtryName(String courtryName) {
		this.courtryName = courtryName;
	}

	@XmlElement(name = "Latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@XmlElement(name = "Longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "IpResponse [ip=" + ip + ", courtryName=" + courtryName + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
