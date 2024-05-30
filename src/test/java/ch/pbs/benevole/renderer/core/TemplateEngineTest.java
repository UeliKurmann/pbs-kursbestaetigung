package ch.pbs.benevole.renderer.core;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineTest {

	@Test
	public void testSimpleVariableReplacement() {
		final TemplateEngine testee = new TemplateEngine();
		testee.add("x", "y");
		final String actual = testee.process("${x}");
		assertThat(actual).isEqualTo("y");
	}

	@Test
	public void testMultipleReplacementOfTheSameVariable() {
		final TemplateEngine testee = new TemplateEngine();
		testee.add("x", "y");
		final String actual = testee.process("${x} x ${x}");
		assertThat(actual).isEqualTo("y x y");
	}

	@Test
	public void testMultipleVariableReplacement() {
		final TemplateEngine testee = new TemplateEngine();
		testee.add("x", "1");
		testee.add("y", "2");
		final String actual = testee.process("${x} ${y}");
		assertThat(actual).isEqualTo("1 2");
	}

	@Test
	public void testVariableReplacementWithSupplier() {
		final TemplateEngine testee = new TemplateEngine();
		testee.add("x", () -> "abcd");
		final String actual = testee.process("${x}");
		assertThat(actual).isEqualTo("abcd");
	}

	@Test
	public void testFixedTodayVariable() {
		final TemplateEngine testee = new TemplateEngine();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		final String actual = testee.process("${today}");
		assertThat(actual).isEqualTo(sdf.format(new Date()));
	}

}
