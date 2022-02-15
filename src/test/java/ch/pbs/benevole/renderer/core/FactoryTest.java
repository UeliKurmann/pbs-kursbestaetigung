package ch.pbs.benevole.renderer.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FactoryTest {

	@Test
	public void pdfDocumentFactory() {
		final Factory testee = new Factory();
		final PdfDocument pdfDocumentMock = Mockito.mock(PdfDocument.class);
		testee.configPdfDocument(() -> pdfDocumentMock);
		Assertions.assertThat(testee.getPdfDocument()).isEqualTo(pdfDocumentMock);
	}

	@Test
	public void templateEngineFactory() {
		final Factory testee = new Factory();
		final TemplateEngine templateEngineMock = Mockito.mock(TemplateEngine.class);
		testee.configTemplateEngine(() -> templateEngineMock);
		Assertions.assertThat(testee.getTemplateEngine()).isEqualTo(templateEngineMock);
	}

}
