package ch.pbs.benevole.renderer.core;

public class PdfText {

	public enum Style {
		normal, bold, italic
	};
	
	private String value;
	private Style style;
	private boolean newline;
	
	private PdfText(String value, Style style, boolean newline){
		this.value = value;
		this.style = style;
		this.newline = newline;
	}
	
	public static PdfText create(String value, Style style, boolean newline){
		return new PdfText(value, style, newline);
	}
	
	public String getValue() {
		return value.replace("\n", " ").replaceAll("(\\s){2,}"," ").trim();
	}
	
	public Style getStyle() {
		return style;
	}
	
	void setValue(String value) {
		this.value = value;
	}
	
	public boolean isNewline() {
		return newline;
	}

}
