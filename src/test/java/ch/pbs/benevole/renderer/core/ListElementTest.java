package ch.pbs.benevole.renderer.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import ch.pbs.benevole.renderer.core.PdfText.Style;

public class ListElementTest {

	@Test
	public void hasNoSubListElements() {
		PdfText pdfText = PdfText.create("hallo", Style.bold, false);
		ListElement testee = ListElement.create(Lists.newArrayList(pdfText));
		List<PdfText> actual = testee.getValue();
		Assert.assertThat(actual.get(0), is(equalTo(pdfText)));
		Assert.assertFalse(testee.hasSubListElements());
	}

	@Test
	public void hasSubListElements() {
		PdfText pdfText = PdfText.create("hallo", Style.bold, false);
		ListElement testee = ListElement.create(Lists.newArrayList(pdfText), ListElement.create(Lists.newArrayList(PdfText.create("a", Style.bold, false))));
		List<PdfText> actual = testee.getValue();
		Assert.assertThat(actual.get(0), is(equalTo(pdfText)));
		Assert.assertTrue(testee.hasSubListElements());
	}
}
