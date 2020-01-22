package ch.pbs.benevole.renderer.resources;

import java.io.InputStream;

import ch.pbs.benevole.renderer.uc.kursbestaetigung.KursDokumentGenerator;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.KursParameter;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLAccessor;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursConfig;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung;

public class Renderer {

	public static byte[] renderPdf(String kurs, String lang, KursParameterJson kpj) throws Exception {
		XMLKursConfig config = XMLAccessor.readKursConfig(loadConfing(lang));
		String name = createKursTemplateName(kurs, lang);
		XMLKursbeschreibung beschreibung = XMLAccessor.readKursbeschreibung(loadKursTemplate(name));

		KursParameter parameter = new KursParameter();

		parameter.setDauer(kpj.getDauer());
		parameter.setKursOrt(kpj.getKursOrt());
		parameter.setOrganisator(kpj.getOrganisator());

		parameter.setAnrede(kpj.getAnrede());
		parameter.setName(kpj.getName());
		parameter.setVorname(kpj.getVorname());
		parameter.setWohnort(kpj.getWohnort());
		parameter.setGeburtstag(kpj.getGeburtstag());

		return new KursDokumentGenerator().render(config, beschreibung, parameter).toByteArray();
	}

	private static InputStream loadKursTemplate(String name) {
		return BenevoleRendererResource.class.getClassLoader().getResourceAsStream(name);
	}

	private static InputStream loadConfing(String lang) {
		return BenevoleRendererResource.class.getClassLoader().getResourceAsStream("xml/kurs-config-"+lang+".xml");
	}

	private static String createKursTemplateName(String kurs, String lang) {
		return String.format("xml/%s_%s.xml", kurs, lang);
	}

}
