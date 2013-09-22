package epsi.test.samples;

import epsi.test.Test;

public class TestSamples {
	
	@Test
	public void canAdd() {
		int result = 2 + 3;

		if (result != 5) throw new RuntimeException("Invalid result 5 expected but get " + result);
	}

	@Test(description="Ce test échoue volontairement pour montrer une cas d'erreur")
	public void canSubstract() {
		int result = 2 - 2;

		if (result != 1) throw new RuntimeException("Invalid result 1 expected but get " + result);
	}

	@Test(description="Ce test échoue car la méthode de test a des paramètres")
	public void canSubstract(String arg1) {
	}
}
