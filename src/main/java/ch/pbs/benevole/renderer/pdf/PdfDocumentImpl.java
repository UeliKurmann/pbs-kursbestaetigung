package ch.pbs.benevole.renderer.pdf;

import static ch.pbs.benevole.renderer.pdf.ElementFactory.createH1Paragraph;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createH2Paragraph;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createList;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createNameValueTable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfDocument;
import ch.pbs.benevole.renderer.core.PdfDocumentException;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Alignement;
import ch.pbs.benevole.renderer.core.PdfText.Style;
import ch.pbs.benevole.renderer.core.TemplateEngine;

public class PdfDocumentImpl implements PdfDocument {

	private Document document;
	private ByteArrayOutputStream os;
	private final TemplateEngine engine;
	private PdfWriter pdfWriter;

	private PdfDocumentImpl() throws PdfDocumentException {
		this.engine = Factory.get().getTemplateEngine();
		try {
			document = new Document();
			document.setMargins(87, 36, 10, 20);
			os = new ByteArrayOutputStream();
			pdfWriter = PdfWriter.getInstance(document, os);
			setPassword(pdfWriter);
			document.open();
		} catch (final DocumentException e) {
			PdfDocumentException.create("Error creating document.", e);
		}
	}

	public static PdfDocument create() {
		try {
			return new PdfDocumentImpl();
		} catch (final PdfDocumentException e) {
			throw new RuntimeException(e);
		}
	}

	public void setMetadata(final String title, final String author, final String creator) {
		addMetaData(document, title, author, creator);
	}

	@Override
	public void addH1(final String text) throws PdfDocumentException {
		add(createH1Paragraph(text.toUpperCase()));
	}

	@Override
	public void addH2(final String text) throws PdfDocumentException {
		add(createH2Paragraph(text.toUpperCase()));
	}

	@Override
	public void addText(final List<PdfText> text) throws PdfDocumentException {
		add(ElementFactory.toParagraph(text.stream().map(t -> engine.process(t)).collect(Collectors.toList())));
	}

	@Override
	public void addText(final String text) throws PdfDocumentException {
		addText(Lists.newArrayList(PdfText.create(text, Style.normal, false)));
	}

	@Override
	public void addEmptyParagraph() throws PdfDocumentException {
		add(new Paragraph(new Chunk(" ")));
	}

	@Override
	public void addTable(final NameValue... pairs) throws PdfDocumentException {
		try {
			add(createNameValueTable(pairs));
		} catch (final Exception e) {
			throw PdfDocumentException.create("Could not create table.", e);
		}
	}

	@Override
	public void addList(final ListElement... elements) throws PdfDocumentException {
		add(createList(elements));
	}

	@Override
	public void addHeaderLogo() throws PdfDocumentException {
		try {

			document.add(ElementFactory.createLogo(os, pdfWriter));
		} catch (final Exception e) {
			throw PdfDocumentException.create("Could not create logo.", e);
		}
	}

	@Override
	public void addSignatureLogo(final Alignement alignement) throws PdfDocumentException {
		try {
			document.add(ElementFactory.createSignature(alignement, os, pdfWriter));
		} catch (final Exception e) {
			throw PdfDocumentException.create("Could not create logo.", e);
		}
	}

	private void add(final Element e) throws PdfDocumentException {
		try {
			document.add(e);
		} catch (final Exception t) {
			throw PdfDocumentException.create("Could not add element.", t);
		}
	}

	@Override
	public void write(final File file) throws IOException {
		document.close();
		Files.write(os.toByteArray(), file);
	}

	@Override
	public ByteArrayOutputStream getOutputStream() {
		document.close();
		return os;
	}

	private static void setPassword(final PdfWriter pdfWriter) throws DocumentException {
		final byte[] password = UUID.randomUUID().toString().getBytes();
		pdfWriter.setEncryption(null, password, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
	}

	private static void addMetaData(final Document document, final String title, final String author, final String creator) {
		document.addTitle(title);
		document.addAuthor(author);
		document.addCreator(creator);
		document.addCreationDate();
	}

	@Override
	public void addContext(final String name, final Supplier<String> valueSupplier) {
		this.engine.add(name, valueSupplier);

	}

}
