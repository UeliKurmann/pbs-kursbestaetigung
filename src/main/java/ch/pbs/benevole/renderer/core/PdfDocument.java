package ch.pbs.benevole.renderer.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

public interface PdfDocument {

	void addH1(String text) throws PdfDocumentException;

	void addH2(String text) throws PdfDocumentException;

	void addText(PdfText text) throws PdfDocumentException;

	void addText(String text) throws PdfDocumentException;

	void addEmptyParagraph() throws PdfDocumentException;

	void addTable(NameValue... pairs) throws PdfDocumentException;

	void addList(ListElement... elements) throws PdfDocumentException;

	void addHeaderLogo() throws PdfDocumentException;

	void write(File file) throws IOException;

	ByteArrayOutputStream getOutputStream();
	
	void addContext(String name, Supplier<String> valueSupplier);

}