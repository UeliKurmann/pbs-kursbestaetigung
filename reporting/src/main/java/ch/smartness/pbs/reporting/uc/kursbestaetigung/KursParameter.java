package ch.smartness.pbs.reporting.uc.kursbestaetigung;

public class KursParameter {

	private String name;
	private String vorname;
	private String wohnort;
	private String dauer;
	private String kursOrt;
	private String organisator;
	private String geburtstag;
	private String anrede;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public String getDauer() {
		return dauer;
	}

	public void setDauer(String dauer) {
		this.dauer = dauer;
	}

	public String getKursOrt() {
		return kursOrt;
	}

	public void setKursOrt(String kursOrt) {
		this.kursOrt = kursOrt;
	}

	public String getOrganisator() {
		return organisator;
	}

	public void setOrganisator(String organisator) {
		this.organisator = organisator;
	}
	
	public String getGeburtstag() {
		return geburtstag;
	}
	
	public void setGeburtstag(String geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getAnrede() {
		return this.anrede;
	}
	
	public void setAnrede(String anrede){
		this.anrede = anrede;
	}

}
