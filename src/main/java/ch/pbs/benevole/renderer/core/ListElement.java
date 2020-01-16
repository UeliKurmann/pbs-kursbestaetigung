package ch.pbs.benevole.renderer.core;

public class ListElement {

	private PdfText value;
	private ListElement[] subListElements;
	
	private ListElement(PdfText value, ListElement... subListElements){
		this.subListElements = subListElements;
		this.value = value;
	}
	
	public static ListElement create(PdfText value, ListElement... subListElements){
		return new ListElement(value, subListElements);
	}
	
	public PdfText getValue() {
		return value;
	}
	
	public boolean hasSubListElements(){
		return this.subListElements != null && this.subListElements.length > 0;
	}
	
	public ListElement[] getSubListElements() {
		return subListElements;
	}
	

}
