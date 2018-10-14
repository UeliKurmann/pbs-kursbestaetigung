package ch.smartness.pbs.reporting.uc.kursbestaetigung.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLText {

	@XmlElement(required = false)
	private XMLText text;

	@XmlElement(required = true)
	private String value;

	@XmlAttribute(required = false)
	private XMLTextStyle style;

	public XMLText getText() {
		return text;
	}

	public void setText(XMLText text) {
		this.text = text;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setStyle(XMLTextStyle style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public XMLTextStyle getStyle() {
		return style;
	}

}
