package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;

public class XMLAccessor {
	
	public static XMLKursbeschreibung readKursbeschreibung(InputStream xmlDocument) throws Exception{
		JAXBContext jaxbContext = JAXBContext.newInstance(XMLKursbeschreibung.class, XMLKursbeschreibung.InhaltElement.class, XMLText.class, XMLTextStyle.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (XMLKursbeschreibung)unmarshaller.unmarshal(xmlDocument);
	}
	
	public static XMLKursConfig readKursConfig(InputStream xmlDocument) throws Exception{
		JAXBContext jaxbContext = JAXBContext.newInstance(XMLKursConfig.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (XMLKursConfig)unmarshaller.unmarshal(xmlDocument);
	}
	
}
