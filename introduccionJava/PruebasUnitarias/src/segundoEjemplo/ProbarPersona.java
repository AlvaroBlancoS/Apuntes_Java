package segundoEjemplo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ProbarPersona {

	@Test
	void NotNull() {
		boolean isNull = false;
		for (Persona e : Datos.lista()) {
			isNull = true;
		}

		assertTrue(isNull);

	}

	@Test
	void isNull() {
		ArrayList<Persona> prueba = new ArrayList<>();
		boolean isNull = false;
		for (Persona persona : prueba) {
			isNull = false;
		}

		assertFalse(isNull);

	}

	@Test
	void isEqual() {
		assertEquals(Datos.lista().get(0).getId(), 1);

	}

	@Test
	void isNoEqual() {
		assertNotEquals(Datos.lista().get(0).getId(), Integer.MAX_VALUE);

	}

	@Test
	void isNameNotEqual() {
		for (Persona p : Datos.lista()) {
			assertNotEquals(p.getNombre(), "Caraculo");
			assertNotEquals(p.getApellidos(), "feo");

		}
	}

}
