package ch.pbs.benevole.renderer.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import ch.pbs.benevole.renderer.core.PdfText.Style;

public class ListElementTest {

	@Test
	public void hasNoSubListElements() {
		final PdfText pdfText = PdfText.create("hallo", Style.bold, false);
		final ListElement testee = ListElement.create(Lists.newArrayList(pdfText));
		final List<PdfText> actual = testee.getValue();
		assertThat(actual.get(0)).isEqualTo(pdfText);
		assertThat(testee.hasSubListElements()).isFalse();
	}

	@Test
	public void hasSubListElements() {
		final PdfText pdfText = PdfText.create("hallo", Style.bold, false);
		final ListElement testee = ListElement.create(Lists.newArrayList(pdfText),
				ListElement.create(Lists.newArrayList(PdfText.create("a", Style.bold, false))));
		final List<PdfText> actual = testee.getValue();
		assertThat(actual.get(0)).isEqualTo(pdfText);
		assertThat(testee.hasSubListElements()).isTrue();
	}
}
