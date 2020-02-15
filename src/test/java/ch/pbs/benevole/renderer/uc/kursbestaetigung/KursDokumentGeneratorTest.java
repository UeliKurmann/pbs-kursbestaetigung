package ch.pbs.benevole.renderer.uc.kursbestaetigung;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ch.pbs.benevole.renderer.core.Language;

public class KursDokumentGeneratorTest {
	
	@Test
	public void frenchWohnortLe() {
		KursDokumentGenerator testee = new KursDokumentGenerator();
		String actual = testee.processWohnort("Le Locle", Language.fr);
		assertThat(actual, is(equalTo("au Locle")));
	}

	@Test
	public void frenchWohnortLes() {
		KursDokumentGenerator testee = new KursDokumentGenerator();
		String actual = testee.processWohnort("Les Diablerets", Language.fr);
		assertThat(actual, is(equalTo("aux Diablerets")));
	}
	
	@Test
	public void frenchWohnort() {
		KursDokumentGenerator testee = new KursDokumentGenerator();
		String actual = testee.processWohnort("Lausanne", Language.fr);
		assertThat(actual, is(equalTo("à Lausanne")));
	}
	
	@Test
	public void frenchWohnortLa() {
		KursDokumentGenerator testee = new KursDokumentGenerator();
		String actual = testee.processWohnort("La Chaux-des-Fonds", Language.fr);
		assertThat(actual, is(equalTo("à La Chaux-des-Fonds")));
	}
}
