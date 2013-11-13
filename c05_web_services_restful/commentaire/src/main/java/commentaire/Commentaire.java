package commentaire;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Commentaire {

	private String contenu;
	private String auteur;
	private long timestamp;
	
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
