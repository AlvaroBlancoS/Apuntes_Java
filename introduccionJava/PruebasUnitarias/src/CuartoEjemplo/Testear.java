package CuartoEjemplo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Testear {
	ProbarNumeros pn;

	@BeforeAll
	void primerInicio() {
		pn = new ProbarNumeros();
	}

	@Test
	void metodoElMayorPrimeraPrueba() {
		int[] pru = { 4, 2, 8, 1, 34, 2, 8, 5 };
		int resul = pn.elMayor(pru);
		assertEquals(resul, 34);

	}

	@Test
	void MetodoMayorSegundaPrueba() {
		int[] pru = {};
		int resul = pn.elMayor(pru);
		assertEquals(resul, 0);

	}
	
	@Test
	void MetodoMayorTerceraPrueba() {
		int[] pru = {4, 2, 8, 1, 34, 2, 8, 5};
		int result = pn.elMayor(pru);
		assertNotEquals(result, 0);

	}
	
	@Test
	void MetodoMayorSiEsUnTrue() {
		int[] pru = {4, 2, 8, 1, 34, 2, 8, 5};
		int result = pn.elMayor(pru);
		boolean ok = false;
		if (result <0) {
			ok = true;
			assertTrue(ok);
		}else {
			assertFalse(ok);
		}
	
	}
	
	
	

	@AfterAll
	void seAcabaLosTests() {
		pn = null;
	}

}
