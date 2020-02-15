package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLText {


	@XmlValue
	private String value;

	@XmlAttribute(required = false)
	private XMLTextStyle style;
	
	@XmlAttribute(required = false)
	private XMLTextAlignement alignement;
	
	@XmlAttribute(required = false)
	private boolean newline;

	public void setValue(String value) {
		this.value = value;
	}

	public void setStyle(XMLTextStyle style) {
		this.style = style;
	}
	
	public void setAlignement(XMLTextAlignement alignement) {
		this.alignement = alignement;
	}

	public String getValue() {
		return value;
	}

	public XMLTextStyle getStyle() {
		return style;
	}
	
	public XMLTextAlignement getAlignement() {
		return alignement;
	}
	
	public boolean isNewline() {
		return newline;
	}

}
