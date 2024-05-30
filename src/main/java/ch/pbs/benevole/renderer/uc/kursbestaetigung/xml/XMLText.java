package ch.pbs.benevole.renderer.uc.kursbestaetigung.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLText {


	@XmlValue
	private String value;

	@XmlAttribute()
	private XMLTextStyle style;
	
	@XmlAttribute()
	private XMLTextAlignement alignement;
	
	@XmlAttribute()
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
