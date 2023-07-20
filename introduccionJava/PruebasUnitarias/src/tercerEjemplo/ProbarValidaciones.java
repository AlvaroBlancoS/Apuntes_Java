package tercerEjemplo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


/**
 * TestInstance : Se usa para configurar el ciclo de vida las instancias de
 * prueba para la clase de prueba anotada o la interfaz de prueba
 * 
 * PER_CLASS: se creará una nueva instancia de prueba una vez por clase de
 * prueba
 * 
 * PER_METHOD: Se creará una nueva instancia de prueba para cada método de
 * prueba, método de fábrica de prueba o método de plantilla de prueba. Este
 * modo es análogo al comportamiento encontrado en las versiones 1 a 4 de JUnit.
 * 
 * Si TestInstance no se declara explícitamente en una clase de prueba o en una
 * interfaz de prueba implementada por una clase de prueba, el modo de ciclo de
 * vida se establecerá implícitamente en PER_METHOD
 * 
 * @author Álvaro Blanco Sanginés
 *
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProbarValidaciones {

	Validaciones val;

	/**
	 * Se arranca el primero del todo
	 */
	@BeforeAll
	void metodoquesehacenantesdetodo() {
		val = new Validaciones();
		System.out.println("Ejecutando validaciones...");

	}

	/**
	 * Una vez realizado la ejecución con BeforeAll se ejecutará después el primer
	 * test
	 *
	 */
	@Test
	void probarDNI() {
//		System.out.println("Probando DNI...");

		boolean resultado1 = val.comprobarDNI("11111111H");

		assertTrue(resultado1);

		boolean resultado2 = val.comprobarDNI("11113311H");
		assertFalse(resultado2);

		boolean resultado3 = val.comprobarDNI("13452345234523452345235423452345432X");
		assertFalse(resultado3);

	}

	@Test
	void probarNIE() {
		boolean resultado1 = val.comprobarNIE("Y5712514H");
		assertTrue(resultado1);

		boolean resultado2 = val.comprobarNIE("11113311H");
		assertFalse(resultado2);

		boolean resultado3 = val.comprobarNIE("13452345234523452345235423452345432X");
		assertFalse(resultado3);
	}

	/**
	 * El primer test termina la primera ejecución y antes de ejecutar el segundo,
	 * lo primero del todo es ejecutar de este método que se hace antes de cada
	 * prueba
	 */
	@BeforeEach
	void metodoquesehacenantesdecadacasodeprueba() {
		// Agregar.info("Prueba realizada");
		System.out.println("Prueba realizada");
	}

	/**
	 * Devuelve un nulo después de realizar tests
	 */
	@AfterAll
	void metodoquesehacenalacabartodo() {
		val = null;
	}

}
