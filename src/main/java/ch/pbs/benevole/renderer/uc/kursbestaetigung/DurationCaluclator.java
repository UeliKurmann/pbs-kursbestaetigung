package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DurationCaluclator {

	public int calculateDuration(String dates) {
		String[] split = dates.split("\n");
		return Arrays.stream(split).map(this::processLine).reduce(0, Integer::sum);
	}

	private int processLine(String dates) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String[] twoDates = dates.split("-");
		if (twoDates.length == 1) {
			return 1;
		} else if (twoDates.length == 2) {
			LocalDate d1 = LocalDate.parse(twoDates[0].trim(), formatter);
			LocalDate d2 = LocalDate.parse(twoDates[1].trim(), formatter);
			int days = Period.between(d1, d2).getDays();
			if (days < 0) {
				throw new IllegalArgumentException("Invalid duration: " + dates);
			}
			return days + 1;
		} else {
			throw new IllegalStateException("No valid date string.");
		}
	}

}
