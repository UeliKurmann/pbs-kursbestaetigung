package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kurs-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLKursConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private String titel;

	@XmlElement(required = true)
	private String inhalt;

	@XmlElementWrapper(name ="intro")
	@XmlElement(name="text")
	private List<XMLText> intro;

	@XmlElement(required = true)
	private String tabKurs;
	
	@XmlElement(required = true)
	private String tabOrt;
	
	@XmlElement(required = true)
	private String tabDatum;
	
	@XmlElement(required = true)
	private String tabOrganisator;
	
	@XmlElement(required = true)
	private String abschluss;
	
	@XmlElement(required = true)
	private String verantwortlicher;

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public List<XMLText> getIntro() {
		return intro;
	}

	public void setIntro(List<XMLText> intro) {
		this.intro = intro;
	}

	public String getTabKurs() {
		return tabKurs;
	}

	public void setTabKurs(String tabKurs) {
		this.tabKurs = tabKurs;
	}

	public String getTabOrt() {
		return tabOrt;
	}

	public void setTabOrt(String tabOrt) {
		this.tabOrt = tabOrt;
	}

	public String getTabDatum() {
		return tabDatum;
	}

	public void setTabDatum(String tabDatum) {
		this.tabDatum = tabDatum;
	}

	public String getTabOrganisator() {
		return tabOrganisator;
	}

	public void setTabOrganisator(String tabOrganisator) {
		this.tabOrganisator = tabOrganisator;
	}

	public String getAbschluss() {
		return abschluss;
	}

	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}

	public String getVerantwortlicher() {
		return verantwortlicher;
	}

	public void setVerantwortlicher(String verantwortlicher) {
		this.verantwortlicher = verantwortlicher;
	}
	
	
}
