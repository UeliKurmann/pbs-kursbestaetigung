package ch.pbs.benevole.renderer.core;

import java.util.List;

public class ListElement {

	private final List<PdfText> value;
	private final ListElement[] subListElements;
	
	private ListElement(List<PdfText> value, ListElement... subListElements){
		this.subListElements = subListElements;
		this.value = value;
	}
	
	public static ListElement create(List<PdfText> value, ListElement... subListElements){
		return new ListElement(value, subListElements);
	}
	
	public List<PdfText> getValue() {
		return value;
	}
	
	public boolean hasSubListElements(){
		return this.subListElements != null && this.subListElements.length > 0;
	}
	
	public ListElement[] getSubListElements() {
		return subListElements;
	}
	

}
