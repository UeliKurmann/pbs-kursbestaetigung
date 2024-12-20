package ch.pbs.benevole.renderer.resources;

import java.io.InputStream;

import ch.pbs.benevole.renderer.core.Language;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.KursDokumentGenerator;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.KursParameter;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLAccessor;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursConfig;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung;

public class Renderer {

	public static byte[] renderPdf(String kurs, String lang, KursParameterJson kpj) throws Exception {
		XMLKursConfig config = XMLAccessor.readKursConfig(loadConfig(lang));
		String name = createKursTemplateName(kurs.toLowerCase().trim(), lang.toLowerCase().trim());
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

		return new KursDokumentGenerator().render(Language.valueOf(lang), config, beschreibung, parameter).toByteArray();
	}

	private static InputStream loadKursTemplate(String name) {
		return BenevoleRendererResource.class.getClassLoader().getResourceAsStream(name);
	}

	private static InputStream loadConfig(String lang) {
		String name = String.format("xml/%1$s/kurs-config-%1$s.xml",lang);
		return BenevoleRendererResource.class.getClassLoader().getResourceAsStream(name);
	}

	private static String createKursTemplateName(String kurs, String lang) {
		return String.format("xml/%1$s/%2$s_%1$s.xml", lang, kurs);
	}

}
