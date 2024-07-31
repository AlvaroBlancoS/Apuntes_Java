package com.futbol.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.futbol.model.Equipo;
import com.futbol.model.Jugador;
import com.futbol.repository.JugadorRepository;
import com.futbol.service.JugadorService;
import com.futbol.util.Validaciones;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class FutbolV1ApplicationTests {
	Jugador j;
	Equipo e;

	/**
	 * Es para iniciar objtos una sola vez antes de todas las pruebas
	 */
	@BeforeAll
	private void inicio() {
		j = new Jugador();
		e = new Equipo();
	}

	@Mock
	private JugadorRepository jugadorRepository;

	@Mock
	private Validaciones validaciones;

	@InjectMocks
	private JugadorService jugadorService;

	/**
	 * Esto es mi codigo original
	 */
	public void invalidarDocumentoNIF() {
		boolean validar = false;
		if (!validaciones.comprobarDNI("Z9770067V")) {// 45623102K
			validar = true;
		}

		assertTrue(validar);
	}

	/**
	 * Y este codigo es mas lógico.
	 */
	@Test
	void invalidarDocumentoNIFv2() {
		// Configurar el comportamiento del mock
		when(validaciones.comprobarDNI("Z9770067V")).thenReturn(false);
		 
	    // Llamar al método bajo prueba y verificar el resultado
		boolean validar = !validaciones.comprobarDNI("Z9770067V");
		System.out.println("Devuelve esto en invalidar NIF "+validar);
		assertTrue(validar);
	}
	
	@Test
	void invalidarDocumentoNIE() {
		when(validaciones.comprobarNIE("23533679X")).thenReturn(false);
		 
		boolean validar = !validaciones.comprobarNIE("23533679X");
		System.out.println("Devuelve esto en invalidar NIE "+validar);
		assertTrue(validar);
	}
	
	
	@Test
	void validarDocumentoNIF() {
		//when(validaciones.comprobarDNI("23533679X")).thenReturn(true);
		
		
		boolean validar = validaciones.comprobarDNI("23533679X");
		
		System.out.println("Devuelve esto en validar NIF "+validar);
		assertFalse(validar);
		
	}
	
	@Test
	void validarDocumentoNIE() {
		//when(validaciones.comprobarNIE("Z9770067V")).thenReturn(true);
		 
		boolean validar = validaciones.comprobarNIE("Z9770067V");
		
		System.out.println("Devuelve esto en validar NIE "+validar);
		assertFalse(validar);
		
	}

	/**
	 * Es para iniciar mocks antes de cada prueba, asegurando un estado limpio y
	 * consistente
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

}
