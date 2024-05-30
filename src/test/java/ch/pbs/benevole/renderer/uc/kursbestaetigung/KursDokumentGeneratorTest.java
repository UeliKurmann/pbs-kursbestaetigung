package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import ch.pbs.benevole.renderer.core.Language;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KursDokumentGeneratorTest {

	@Test
	public void frenchWohnortLe() {
		final KursDokumentGenerator testee = new KursDokumentGenerator();
		final String actual = testee.processWohnort("Le Locle", Language.fr);
		assertThat(actual).isEqualTo("au Locle");
	}

	@Test
	public void frenchWohnortLes() {
		final KursDokumentGenerator testee = new KursDokumentGenerator();
		final String actual = testee.processWohnort("Les Diablerets", Language.fr);
		assertThat(actual).isEqualTo("aux Diablerets");
	}

	@Test
	public void frenchWohnort() {
		final KursDokumentGenerator testee = new KursDokumentGenerator();
		final String actual = testee.processWohnort("Lausanne", Language.fr);
		assertThat(actual).isEqualTo("à Lausanne");
	}

	@Test
	public void frenchWohnortLa() {
		final KursDokumentGenerator testee = new KursDokumentGenerator();
		final String actual = testee.processWohnort("La Chaux-des-Fonds", Language.fr);
		assertThat(actual).isEqualTo("à La Chaux-des-Fonds");
	}
}
