package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.Language;
import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfDocument;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Alignement;
import ch.pbs.benevole.renderer.core.PdfText.Style;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursConfig;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung.InhaltElement;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLText;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLTextAlignement;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLTextStyle;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class KursDokumentGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(KursDokumentGenerator.class);

	public ByteArrayOutputStream render(final Language lang, final XMLKursConfig kursConfig, final XMLKursbeschreibung kursbeschreibung,
			final KursParameter parameter) throws Exception {
		final PdfDocument document = Factory.get().getPdfDocument();
		document.addContext("name", parameter::getName);
		document.addContext("vorname", parameter::getVorname);

		document.addContext("wohnort", () -> processWohnort(parameter.getWohnort(), lang));

		document.addContext("geburtstag", parameter::getGeburtstag);
		document.addContext("anrede", parameter::getAnrede);

		document.addEmptyParagraph();
		document.addH1(kursConfig.getTitel());

		document.addText(toPdfText(kursConfig.getIntro()));

		document.addTable(//
				NameValue.create(kursConfig.getTabKurs(), kursbeschreibung.getName()), //
				NameValue.create(kursConfig.getTabOrt(), parameter.getKursOrt()), //
				NameValue.create(kursConfig.getTabDatum(), createDauerText(parameter, kursConfig)), //
				NameValue.create(kursConfig.getTabOrganisator(), parameter.getOrganisator()));
		document.addEmptyParagraph();
		document.addText(kursbeschreibung.getDescription());
		document.addH2(kursConfig.getInhalt());

		final List<ListElement> elements = new ArrayList<>();
		for (final InhaltElement inhaltElement : kursbeschreibung.getInhalte()) {
			elements.add(ListElement.create(toPdfText(inhaltElement.getText()), t(inhaltElement.getSubInhalt())));
		}

		document.addList(elements.toArray(new ListElement[0]));
		document.addEmptyParagraph();
		document.addText(toPdfText(kursConfig.getAbschluss()));
		document.addEmptyParagraph();

		if (lang == Language.fr) {
			document.addSignatureLogo(Alignement.right);
		} else {
			document.addSignatureLogo(Alignement.left);
		}

		document.addText(toPdfText(Lists.newArrayList(kursConfig.getVerantwortlicher())));
		document.addHeaderLogo();

		return document.getOutputStream();
	}

	private String createDauerText(final KursParameter parameter, final XMLKursConfig config) {
		final String dauer = parameter.getDauer().trim();
		final DurationCaluclator calculator = new DurationCaluclator();
		int calculateDuration = 0;
		try {
			calculateDuration = calculator.calculateDuration(dauer);
		} catch (final Exception e) {
			LOG.error("Could not calculate duration: " + dauer, e);
		}
		if (calculateDuration > 0) {
			return String.format("%s (%s %s)", dauer, calculateDuration, config.getTabDauer());
		} else {
			return dauer;
		}
	}

	protected String processWohnort(final String wohnort, final Language lang) {
		if (wohnort == null) {
			return "";
		}
		if (lang == Language.fr) {
			if (wohnort.toLowerCase().startsWith("le ")) {
				return "au" + wohnort.substring(2);
			} else if (wohnort.toLowerCase().startsWith("les ")) {
				return "aux" + wohnort.substring(3);
			} else {
				return "à " + wohnort;
			}
		} else {
			return wohnort;
		}
	}

	private static ListElement[] t(final List<InhaltElement> subInhalt) {
		final java.util.List<ListElement> elements = new ArrayList<>();
		if (subInhalt != null) {
			for (final InhaltElement i : subInhalt) {
				elements.add(ListElement.create(Lists.newArrayList(toPdfText(i.getText()))));
			}
		}
		return elements.toArray(new ListElement[0]);
	}

	private static List<PdfText> toPdfText(final List<XMLText> texts) {
		final List<PdfText> result = Lists.newArrayList();
		for (final XMLText text : texts) {
			result.add(PdfText.create(text.getValue(), toStyle(text.getStyle()), toAlignement(text.getAlignement()), text.isNewline()));
		}
		return result;

	}

	private static Style toStyle(final XMLTextStyle s) {
		if (s == null) {
			return Style.normal;
		} else {
			return Style.valueOf(s.name());
		}
	}

	private static Alignement toAlignement(final XMLTextAlignement s) {
		if (s == null) {
			return Alignement.left;
		} else {
			return Alignement.valueOf(s.name());
		}
	}

}
