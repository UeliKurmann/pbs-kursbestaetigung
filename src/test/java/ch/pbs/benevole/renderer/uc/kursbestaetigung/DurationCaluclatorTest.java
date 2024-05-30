package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationCaluclatorTest {

	private final DurationCaluclator testee = new DurationCaluclator();

	@Test
	public void multLineTwoDates() {
		final int actual = testee.calculateDuration("01.03.2020 - 04.03.2020\n01.04.2020 - 04.04.2020\n");
		assertThat(actual).isEqualTo(8);
	}

	@Test
	public void multLineMixedDates() {
		final int actual = testee.calculateDuration("01.03.2020 - 04.03.2020\n01.04.2020");
		assertThat(actual).isEqualTo(5);
	}

	@Test
	public void oneDateLineWithDuration() {
		final int actual = testee.calculateDuration("01.03.2020 - 04.03.2020");
		assertThat(actual).isEqualTo(4);
	}

	@Test
	public void oneDateLineWithDuration1() {
		final int actual = testee.calculateDuration("27.09.2017 - 30.09.2018");
		assertThat(actual).isEqualTo(369);
	}

	@Test
	public void oneDateLineOneDate() {
		final int actual = testee.calculateDuration("01.03.2020");
		assertThat(actual).isEqualTo(1);
	}

	@Test
	public void oneDateLineTowDatesDurationNegative() {
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)//
				.isThrownBy(() -> testee.calculateDuration("01.03.2020 - 15.02.2020"));
	}

	@Test
	public void notParsableDate() {
		Assertions.assertThatExceptionOfType(DateTimeParseException.class)//
				.isThrownBy(() -> testee.calculateDuration("01.03.2020 - aName"));

	}
}
