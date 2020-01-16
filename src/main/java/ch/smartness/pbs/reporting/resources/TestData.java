package ch.smartness.pbs.reporting.resources;

public class TestData {
	
	public static KursParameterJson createDemoParameter() {
		KursParameterJson parameter = new KursParameterJson();

		parameter.setDauer("28.09.2017 - 29.09.2017");
		parameter.setKursOrt("Solothurn");
		parameter.setOrganisator("Pfadibewegung Schweiz (PBS)");

		parameter.setAnrede("Herr");
		parameter.setName("Rengel");
		parameter.setVorname("Heinrich");
		parameter.setWohnort("Oberdorf");
		parameter.setGeburtstag("09.08.1977");
		return parameter;
	}

}
