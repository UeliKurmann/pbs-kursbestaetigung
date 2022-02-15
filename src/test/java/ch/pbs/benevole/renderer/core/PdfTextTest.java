package ch.pbs.benevole.renderer.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.pbs.benevole.renderer.core.PdfText.Style;

public class PdfTextTest {

	@Test
	public void factoryMethod() {
		final PdfText testee = PdfText.create("someValue", Style.italic, false);
		assertThat(testee.getValue()).isEqualTo("someValue");
		assertThat(testee.getStyle()).isEqualTo(Style.italic);
	}

	@Test
	public void replaceNewLinesWithSingleSpace() {
		final PdfText testee = PdfText.create("a\nb", Style.italic, false);
		assertThat(testee.getValue()).isEqualTo("a b");
	}

	@Test
	public void replaceMultipleSpacesWithOneSpace() {
		final PdfText testee = PdfText.create("a     b", Style.italic, false);
		assertThat(testee.getValue()).isEqualTo("a b");
	}

}
