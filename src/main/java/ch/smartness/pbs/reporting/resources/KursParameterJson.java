package ch.smartness.pbs.reporting.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.ws.rs.FormParam;

public class KursParameterJson {

	private String name;
	private String vorname;
	private String wohnort;
	private String dauer;
	private String kursOrt;
	private String organisator;
	private String geburtstag;
	private String anrede;
	

	@JsonProperty
	public String getName() {
		return name;
	}

	@FormParam("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getVorname() {
		return vorname;
	}

	@FormParam("vorname")
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@JsonProperty
	public String getWohnort() {
		return wohnort;
	}

	@FormParam("wohnort")
	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	@JsonProperty
	public String getDauer() {
		return dauer;
	}

	@FormParam("dauer")
	public void setDauer(String dauer) {
		this.dauer = dauer;
	}

	@JsonProperty
	public String getKursOrt() {
		return kursOrt;
	}

	@FormParam("kursOrt")
	public void setKursOrt(String kursOrt) {
		this.kursOrt = kursOrt;
	}

	@JsonProperty
	public String getOrganisator() {
		return organisator;
	}

	@FormParam("organisator")
	public void setOrganisator(String organisator) {
		this.organisator = organisator;
	}

	@JsonProperty
	public String getGeburtstag() {
		return geburtstag;
	}

	@FormParam("geburtstag")
	public void setGeburtstag(String geburtstag) {
		this.geburtstag = geburtstag;
	}

	@JsonProperty
	public String getAnrede() {
		return this.anrede;
	}

	@FormParam("anrede")
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

}
