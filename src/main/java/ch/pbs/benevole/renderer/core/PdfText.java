package ch.pbs.benevole.renderer.core;

public class PdfText {

	public enum Style {
		normal, bold, italic
	};
	
	private String value;
	private Style style;
	private PdfText pdfText;
	
	private PdfText(String value, Style style, PdfText pdfText){
		this.value = value;
		this.style = style;
		this.pdfText = pdfText;
	}
	
	public static PdfText create(String value, Style style){
		return new PdfText(value, style, null);
	}
	
	public static PdfText create(String value, Style style, PdfText next){
		return new PdfText(value, style, next);
	}
	
	public String getValue() {
		return value.replace("\n", " ").replaceAll("(\\s){2,}"," ").trim();
	}
	
	public Style getStyle() {
		return style;
	}
	
	public PdfText getNext() {
		return pdfText;
	}
	
	public void setNext(PdfText pdfText) {
		this.pdfText = pdfText;
	}
	
	void setValue(String value) {
		this.value = value;
	}

}
