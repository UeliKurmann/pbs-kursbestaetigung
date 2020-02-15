package ch.pbs.benevole.renderer.core;

public class PdfText {

	public enum Style {
		normal, bold, italic
	};
	
	public enum Alignement {
		left, right
	}
	
	private String value;
	private Alignement alignement;
	private Style style;
	private boolean newline;
	
	private PdfText(String value, Style style, Alignement alignement, boolean newline){
		this.value = value;
		this.style = style;
		this.newline = newline;
		this.alignement = alignement;
	}
	
	public static PdfText create(String value, Style style, boolean newline){
		return new PdfText(value, style, Alignement.left, newline);
	}
	
	public static PdfText create(String value, Style style, Alignement alignement, boolean newline){
		return new PdfText(value, style, alignement, newline);
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
	
	public Alignement getAlignement() {
		return alignement;
	}

}
