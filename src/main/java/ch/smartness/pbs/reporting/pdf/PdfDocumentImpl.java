package ch.smartness.pbs.reporting.pdf;

import static ch.smartness.pbs.reporting.pdf.ElementFactory.createH1Paragraph;
import static ch.smartness.pbs.reporting.pdf.ElementFactory.createH2Paragraph;
import static ch.smartness.pbs.reporting.pdf.ElementFactory.createList;
import static ch.smartness.pbs.reporting.pdf.ElementFactory.createNameValueTable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.function.Supplier;

import com.google.common.io.Files;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import ch.smartness.pbs.reporting.core.Factory;
import ch.smartness.pbs.reporting.core.ListElement;
import ch.smartness.pbs.reporting.core.NameValue;
import ch.smartness.pbs.reporting.core.PdfDocument;
import ch.smartness.pbs.reporting.core.PdfDocumentException;
import ch.smartness.pbs.reporting.core.PdfText;
import ch.smartness.pbs.reporting.core.PdfText.Style;
import ch.smartness.pbs.reporting.core.TemplateEngine;

public class PdfDocumentImpl implements PdfDocument {

	private Document document;
	private ByteArrayOutputStream os;
	private TemplateEngine engine;
	private PdfWriter pdfWriter;

	private PdfDocumentImpl() throws PdfDocumentException {
		this.engine = Factory.get().getTemplateEngine();
		try {
			document = new Document();
			document.setMargins(38, 36, 10, 20);
			os = new ByteArrayOutputStream();
			pdfWriter = PdfWriter.getInstance(document, os);
			setPassword(pdfWriter);
			document.open();
		} catch (DocumentException e) {
			PdfDocumentException.create("Error creating document.", e);
		}
	}

	public static PdfDocument create() {
		try {
			return new PdfDocumentImpl();
		} catch (PdfDocumentException e) {
			throw new RuntimeException(e);
		}
	}

	public void setMetadata(String title, String author, String creator) {
		addMetaData(document, title, author, creator);
	}

	@Override
	public void addH1(String text) throws PdfDocumentException {
		add(createH1Paragraph(text));
	}

	@Override
	public void addH2(String text) throws PdfDocumentException {
		add(createH2Paragraph(text));
	}

	@Override
	public void addText(PdfText text) throws PdfDocumentException {
		add(ElementFactory.toParagraph(engine.process(text)));
	}

	@Override
	public void addText(String text) throws PdfDocumentException {
		addText(PdfText.create(text, Style.normal));
	}

	@Override
	public void addEmptyParagraph() throws PdfDocumentException {
		add(new Paragraph(new Chunk(" ")));
	}

	@Override
	public void addTable(NameValue... pairs) throws PdfDocumentException {
		try {
			add(createNameValueTable(pairs));
		} catch (Exception e) {
			throw PdfDocumentException.create("Could not create table.", e);
		}
	}

	@Override
	public void addList(ListElement... elements) throws PdfDocumentException {
		add(createList(elements));
	}

	@Override
	public void addHeaderLogo() throws PdfDocumentException {
		try {
			document.add(ElementFactory.createLogo(os, pdfWriter));
		} catch (Exception e) {
			throw PdfDocumentException.create("Could not create logo.", e);
		}
	}

	private void add(Element e) throws PdfDocumentException {
		try {
			document.add(e);
		} catch (Exception t) {
			throw PdfDocumentException.create("Could not add element.", t);
		}
	}

	@Override
	public void write(File file) throws IOException {
		document.close();
		Files.write(os.toByteArray(), file);
	}

	@Override
	public ByteArrayOutputStream getOutputStream() {
		document.close();
		return os;
	}

	private static void setPassword(PdfWriter pdfWriter) throws DocumentException {
		byte[] password = UUID.randomUUID().toString().getBytes();
		pdfWriter.setEncryption(null, password, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
	}

	private static void addMetaData(Document document, String title, String author, String creator) {
		document.addTitle(title);
		document.addAuthor(author);
		document.addCreator(creator);
		document.addCreationDate();
	}

	@Override
	public void addContext(String name, Supplier<String> valueSupplier) {
		this.engine.add(name, valueSupplier);

	}

}
