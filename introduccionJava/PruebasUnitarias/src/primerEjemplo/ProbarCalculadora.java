package primerEjemplo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProbarCalculadora {

	@Test
	void testSuma() {
		int resultado = Calculadora.suma(2, 3);
		int prueba = 5;
		assertEquals(resultado, prueba);
	}

	@Test
	void tesResta() {
		int resultado = Calculadora.resta(2, 2);
		int prueba = 0;
		assertEquals(resultado, prueba);
	}
	
	@Test
	void testCalculadoraTrue() {
		boolean isResult = false;
		if (Calculadora.resta(2, 2)==0 && Calculadora.suma(2, 3)==5) {
			isResult = true;
		}
		assertTrue(isResult);
	}
	
	@Test
	void testCalculadoraFalse() {
		boolean isResult = false;
		if (Calculadora.resta(5,2)<=1 || Calculadora.resta(5, 2)>5) {
			isResult = true;
		}
		
		assertFalse(isResult);
		
	}
	


}
