package ch.pbs.benevole.renderer.resources;

public class TestData {

	public static KursParameterJson createDemoParameterDe() {
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

	public static KursParameterJson createDemoParameterFr() {
		KursParameterJson parameter = new KursParameterJson();

		parameter.setDauer("28.09.2017 - 29.09.2017");
		parameter.setKursOrt("Lausanne");
		parameter.setOrganisator("Pfadibewegung Schweiz (PBS)");

		parameter.setAnrede("M.");
		parameter.setName("Modèle");
		parameter.setVorname("Maximilian");
		parameter.setWohnort("Les Diablerets");
		parameter.setGeburtstag("09.08.1977");
		return parameter;
	}

	public static KursParameterJson createDemoParameterIt() {
		KursParameterJson parameter = new KursParameterJson();

		parameter.setDauer("28.09.2017 - 29.09.2017");
		parameter.setKursOrt("\n   \n\t\nBelinzona\n   \n");
		parameter.setOrganisator("Pfadibewegung Schweiz (PBS)");
		parameter.setAnrede("sig.");
		parameter.setName("Esempio");
		parameter.setVorname("Ennio");
		parameter.setWohnort("Locarno");
		parameter.setGeburtstag("09.08.1977");
		return parameter;
	}
}
