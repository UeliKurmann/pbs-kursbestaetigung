package ch.pbs.benevole.renderer.core;

public class PdfText {

	public enum Style {
		normal, bold, italic
	};
	
	public enum Alignment {
		left, right
	}
	
	private String value;
	private final Alignment alignment;
	private final Style style;
	private final boolean newline;
	
	private PdfText(String value, Style style, Alignment alignment, boolean newline){
		this.value = value;
		this.style = style;
		this.newline = newline;
		this.alignment = alignment;
	}
	
	public static PdfText create(String value, Style style, boolean newline){
		return new PdfText(value, style, Alignment.left, newline);
	}
	
	public static PdfText create(String value, Style style, Alignment alignment, boolean newline){
		return new PdfText(value, style, alignment, newline);
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
	
	public Alignment getAlignment() {
		return alignment;
	}

}
