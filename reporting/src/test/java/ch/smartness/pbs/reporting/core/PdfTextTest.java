package ch.smartness.pbs.reporting.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import ch.smartness.pbs.reporting.core.PdfText.Style;

public class PdfTextTest {

	@Test
	public void factoryMethod() {
		PdfText testee = PdfText.create("someValue", Style.italic);
		assertThat(testee.getValue(), is(equalTo("someValue")));
		assertThat(testee.getStyle(), is(equalTo(Style.italic)));
	}
	
	@Test
	public void replaceNewLinesWithSingleSpace() {
		PdfText testee = PdfText.create("a\nb", Style.italic);
		assertThat(testee.getValue(), is(equalTo("a b")));
	}
	
	@Test
	public void replaceMultipleSpacesWithOneSpace() {
		PdfText testee = PdfText.create("a     b", Style.italic);
		assertThat(testee.getValue(), is(equalTo("a b")));
	}

}
