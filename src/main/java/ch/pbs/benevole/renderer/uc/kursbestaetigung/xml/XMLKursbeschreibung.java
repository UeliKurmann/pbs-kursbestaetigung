package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "kurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLKursbeschreibung implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private String name;

	@XmlElement(required = true)
	private String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InhaltElement> getInhalte() {
		return inhalte;
	}

	public void setInhalte(List<InhaltElement> inhalte) {
		this.inhalte = inhalte;
	}

	@XmlElement(required = true)
	private String description;

	@XmlElementWrapper(name="inhalte")
	@XmlElement(name="inhalt", required = true)
	private List<InhaltElement> inhalte;

	

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "inhalt")
	public static class InhaltElement implements Serializable {

		@Serial
		private static final long serialVersionUID = 1L;

		@XmlElement(name = "text")
		private List<XMLText> text;

		@XmlElement(name="inhalt")
		private List<InhaltElement> subInhalt;

		public List<XMLText> getText() {
			return text;
		}

		public List<InhaltElement> getSubInhalt() {
			return subInhalt;
		}
	}

}
