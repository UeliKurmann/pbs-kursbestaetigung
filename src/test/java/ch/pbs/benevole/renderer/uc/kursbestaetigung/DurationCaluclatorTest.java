package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.format.DateTimeParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DurationCaluclatorTest {

	private DurationCaluclator testee = new DurationCaluclator();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void multLineTwoDates() {
		int actual = testee.calculateDuration("01.03.2020 - 04.03.2020\n01.04.2020 - 04.04.2020\n");
		assertThat(actual, is(equalTo(8)));
	}
	
	@Test
	public void multLineMixedDates() {
		int actual = testee.calculateDuration("01.03.2020 - 04.03.2020\n01.04.2020");
		assertThat(actual, is(equalTo(5)));
	}
	
	@Test
	public void oneDateLineWithDuration() {
		int actual = testee.calculateDuration("01.03.2020 - 04.03.2020");
		assertThat(actual, is(equalTo(4)));
	}

	@Test
	public void oneDateLineWithDuration1() {
		int actual = testee.calculateDuration("27.09.2017 - 30.09.2018");
		assertThat(actual, is(equalTo(369)));
	}
	
	@Test
	public void oneDateLineOneDate() {
		int actual = testee.calculateDuration("01.03.2020");
		assertThat(actual, is(equalTo(1)));
	}

	@Test
	public void oneDateLineTowDatesDurationNegative() {
		thrown.expect(IllegalArgumentException.class);
		testee.calculateDuration("01.03.2020 - 15.02.2020");
	}

	@Test
	public void notParsableDate() {
		thrown.expect(DateTimeParseException.class);
		testee.calculateDuration("01.03.2020 - aName");
	}
}
