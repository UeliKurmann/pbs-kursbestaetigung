package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLKursbeschreibung implements Serializable {
	
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
		
		private static final long serialVersionUID = 1L;

		@XmlElement(required = true, name = "text")
		private List<XMLText> text;

		@XmlElement(required = false, name="inhalt")
		private List<InhaltElement> subInhalt;

		public List<XMLText> getText() {
			return text;
		}

		public List<InhaltElement> getSubInhalt() {
			return subInhalt;
		}
	}

}
