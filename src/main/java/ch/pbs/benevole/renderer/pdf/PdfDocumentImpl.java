package ch.pbs.benevole.renderer.pdf;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfDocument;
import ch.pbs.benevole.renderer.core.PdfDocumentException;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Style;
import ch.pbs.benevole.renderer.core.TemplateEngine;
import com.google.common.collect.Lists;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static ch.pbs.benevole.renderer.pdf.ElementFactory.createH1Paragraph;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createH2Paragraph;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createList;
import static ch.pbs.benevole.renderer.pdf.ElementFactory.createNameValueTable;

public class PdfDocumentImpl implements PdfDocument {

	private final Document document;
	private final ByteArrayOutputStream os;
	private final TemplateEngine engine;
	private final PdfWriter pdfWriter;

	private PdfDocumentImpl() throws PdfDocumentException {
		this.engine = Factory.get().getTemplateEngine();
		try {
			document = new Document();
			document.setMargins(87, 40, 10, 20);
			os = new ByteArrayOutputStream();
			pdfWriter = PdfWriter.getInstance(document, os);
			setPassword(pdfWriter);
			document.open();
		} catch (final DocumentException e) {
			throw PdfDocumentException.create("Error creating document.", e);
		}
	}

	public static PdfDocument create() {
		try {
			return new PdfDocumentImpl();
		} catch (final PdfDocumentException e) {
			throw new RuntimeException(e);
		}
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
		add(ElementFactory.toParagraph(text.stream().map(engine::process).collect(Collectors.toList())));
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
			document.add(ElementFactory.createLogo(pdfWriter));
		} catch (final Exception e) {
			throw PdfDocumentException.create("Could not create logo.", e);
		}
	}

	@Override
	public void addSignatureLogo(final PdfText.Alignment alignment) throws PdfDocumentException {
		try {
			document.add(ElementFactory.createSignature(alignment));
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
	public ByteArrayOutputStream getOutputStream() {
		document.close();
		return os;
	}

	private static void setPassword(final PdfWriter pdfWriter) throws DocumentException {
		final byte[] password = UUID.randomUUID().toString().getBytes();
		pdfWriter.setEncryption(null, password, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
	}

	@Override
	public void addContext(final String name, final Supplier<String> valueSupplier) {
		this.engine.add(name, valueSupplier);

	}

}
