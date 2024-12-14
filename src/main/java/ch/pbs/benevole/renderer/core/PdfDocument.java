package ch.pbs.benevole.renderer.core;

import ch.pbs.benevole.renderer.core.PdfText.Alignment;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.function.Supplier;

public interface PdfDocument {

	void addH1(String text) throws PdfDocumentException;

	void addH2(String text) throws PdfDocumentException;

	void addText(List<PdfText> text) throws PdfDocumentException;

	void addText(String text) throws PdfDocumentException;

	void addEmptyParagraph() throws PdfDocumentException;

	void addTable(NameValue... pairs) throws PdfDocumentException;

	void addList(ListElement... elements) throws PdfDocumentException;

	void addHeaderLogo() throws PdfDocumentException;

	ByteArrayOutputStream getOutputStream();
	
	void addContext(String name, Supplier<String> valueSupplier);

	void addSignatureLogo(Alignment right) throws PdfDocumentException;

}