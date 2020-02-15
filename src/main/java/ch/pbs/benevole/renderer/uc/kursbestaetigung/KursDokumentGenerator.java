package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfDocument;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Style;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursConfig;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLKursbeschreibung.InhaltElement;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLText;
import ch.pbs.benevole.renderer.uc.kursbestaetigung.xml.XMLTextStyle;
import jersey.repackaged.com.google.common.collect.Lists;

public class KursDokumentGenerator {

	public ByteArrayOutputStream render(XMLKursConfig kursConfig, XMLKursbeschreibung kursbeschreibung, KursParameter parameter) throws Exception{
		PdfDocument document = Factory.get().getPdfDocument();
		document.addContext("name", ()->parameter.getName());
		document.addContext("vorname", ()->parameter.getVorname());
		document.addContext("wohnort", ()->parameter.getWohnort());
		document.addContext("geburtstag", ()->parameter.getGeburtstag());
		document.addContext("anrede", ()->parameter.getAnrede());
		

		document.addHeaderLogo();
		document.addH1(kursConfig.getTitel());
		
		document.addText(toPdfText(kursConfig.getIntro()));
		
		document.addTable(//
				NameValue.create(kursConfig.getTabKurs(), kursbeschreibung.getName()), //
				NameValue.create(kursConfig.getTabOrt(), parameter.getKursOrt()), //
				NameValue.create(kursConfig.getTabDatum(), parameter.getDauer()), //
				NameValue.create(kursConfig.getTabOrganisator(), parameter.getOrganisator()));
		document.addEmptyParagraph();
		document.addText(kursbeschreibung.getDescription());
		document.addH2(kursConfig.getInhalt());

		List<ListElement> elements = new ArrayList<>();
		for (InhaltElement inhaltElement : kursbeschreibung.getInhalte()) {
			elements.add(ListElement.create(toPdfText(inhaltElement.getText()), t(inhaltElement.getSubInhalt())));
		}

		document.addList(elements.toArray(new ListElement[0]));
		document.addEmptyParagraph();
		document.addText(toPdfText(kursConfig.getAbschluss()));
		document.addEmptyParagraph();
		document.addSignatureLogo();
		document.addText(kursConfig.getVerantwortlicher());

		
		return document.getOutputStream();
	}

	private static ListElement[] t(List<InhaltElement> subInhalt) {
		java.util.List<ListElement> elements = new ArrayList<>();
		if (subInhalt != null) {
			for (InhaltElement i : subInhalt) {
				elements.add(ListElement.create(Lists.newArrayList(toPdfText(i.getText()))));
			}
		}
		return elements.toArray(new ListElement[0]);
	}
	
	private static List<PdfText> toPdfText(List<XMLText> texts) {
		List<PdfText> result = Lists.newArrayList();
		for(XMLText text:texts) {
			result.add(PdfText.create(text.getValue(), toStyle(text.getStyle()), text.isNewline()));
		}
		return result;
		
	}
	
	private static Style toStyle(XMLTextStyle s){
		if(s == null){
			return Style.normal;
		}else{
			return Style.valueOf(s.name());
		}
	}

}
