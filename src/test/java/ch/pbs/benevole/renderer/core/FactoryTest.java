package ch.pbs.benevole.renderer.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.Mockito;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.PdfDocument;
import ch.pbs.benevole.renderer.core.TemplateEngine;

public class FactoryTest {
	
	@Test 
	public void pdfDocumentFactory(){
		Factory testee = new Factory();
		PdfDocument pdfDocumentMock = Mockito.mock(PdfDocument.class);
		testee.configPdfDocument(()->pdfDocumentMock);
		assertThat(testee.getPdfDocument(), is(equalTo(pdfDocumentMock)));
	}
	
	@Test 
	public void templateEngineFactory(){
		Factory testee = new Factory();
		TemplateEngine templateEngineMock = Mockito.mock(TemplateEngine.class);
		testee.configTemplateEngine(()->templateEngineMock);
		assertThat(testee.getTemplateEngine(), is(equalTo(templateEngineMock)));
	}

}
