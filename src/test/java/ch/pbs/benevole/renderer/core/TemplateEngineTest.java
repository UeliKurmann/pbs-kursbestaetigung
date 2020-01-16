package ch.pbs.benevole.renderer.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ch.pbs.benevole.renderer.core.TemplateEngine;

public class TemplateEngineTest {

	@Test
	public void testSimpleVariableReplacement() {
		TemplateEngine testee = new TemplateEngine();
		testee.add("x", "y");
		String actual = testee.process("${x}");
		assertThat(actual, is(equalTo("y")));
	}

	@Test
	public void testMultipleReplacementOfTheSameVariable() {
		TemplateEngine testee = new TemplateEngine();
		testee.add("x", "y");
		String actual = testee.process("${x} x ${x}");
		assertThat(actual, is(equalTo("y x y")));
	}

	@Test
	public void testMultipleVariableReplacement() {
		TemplateEngine testee = new TemplateEngine();
		testee.add("x", "1");
		testee.add("y", "2");
		String actual = testee.process("${x} ${y}");
		assertThat(actual, is(equalTo("1 2")));
	}
	
	@Test
	public void testVariableReplacementWithSupplier() {
		TemplateEngine testee = new TemplateEngine();
		testee.add("x", () -> "abcd");
		String actual = testee.process("${x}");
		assertThat(actual, is(equalTo("abcd")));
	}
	
	@Test
	public void testFixedTodayVariable(){
		TemplateEngine testee = new TemplateEngine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String actual = testee.process("${today}");
		assertThat(actual, is(equalTo(sdf.format(new Date()))));
	}
	
}
