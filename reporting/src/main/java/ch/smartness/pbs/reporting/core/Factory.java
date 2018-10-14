package ch.smartness.pbs.reporting.core;

import java.util.function.Supplier;

public class Factory {

	private static final Factory INSTANCE = new Factory();

	private Supplier<PdfDocument> pdfDocumentSupplier;
	private Supplier<TemplateEngine> templateEngineSupplier;

	public static Factory get() {
		return INSTANCE;
	}

	public void configPdfDocument(Supplier<PdfDocument> pdfDocumentSupplier) {
		this.pdfDocumentSupplier = pdfDocumentSupplier;
	}

	public PdfDocument getPdfDocument() {
		return this.pdfDocumentSupplier.get();
	}
	
	public TemplateEngine getTemplateEngine() {
		return templateEngineSupplier.get();
	}

	public void configTemplateEngine(Supplier<TemplateEngine> templateEngineSupplier) {
		this.templateEngineSupplier = templateEngineSupplier;
	}

}
