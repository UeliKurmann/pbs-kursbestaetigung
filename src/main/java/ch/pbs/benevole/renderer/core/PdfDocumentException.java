package ch.pbs.benevole.renderer.core;

import java.io.Serial;

public class PdfDocumentException extends Exception{
	
	@Serial
	private static final long serialVersionUID = 1L;

	public PdfDocumentException(String text, Throwable t) {
		super(text, t);
	}
	
	public static PdfDocumentException create(String text, Throwable t){
		return new PdfDocumentException(text, t);
	}

}
