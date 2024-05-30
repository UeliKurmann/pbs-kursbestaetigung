package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "kurs-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLKursConfig implements Serializable {

	@Serial
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
	private String tabDauer;
	
	@XmlElement(required = true)
	private String tabDatum;
	
	@XmlElement(required = true)
	private String tabOrganisator;
	
	@XmlElementWrapper(name ="abschluss")
	@XmlElement(name="text", required = true)
	private List<XMLText> abschluss;
	
	@XmlElement(required = true)
	private XMLText verantwortlicher;

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

	public List<XMLText> getAbschluss() {
		return abschluss;
	}

	public void setAbschluss(List<XMLText> abschluss) {
		this.abschluss = abschluss;
	}

	public XMLText getVerantwortlicher() {
		return verantwortlicher;
	}

	public void setVerantwortlicher(XMLText verantwortlicher) {
		this.verantwortlicher = verantwortlicher;
	}
	
	public void setTabDauer(String tabDauer) {
		this.tabDauer = tabDauer;
	}
	
	public String getTabDauer() {
		return tabDauer;
	}
	
}
