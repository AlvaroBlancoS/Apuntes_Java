package com.futbol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.futbol.model.Equipo;
import com.futbol.model.Jugador;
import com.futbol.repository.JugadorRepository;
import com.futbol.util.EquipoNotFoundException;
import com.futbol.util.JugadorBadRequest;
import com.futbol.util.JugadorNotFoundException;
import com.futbol.util.Validaciones;

@Service
@Validated // Esta notacion es fundametal para que funcione validaciones como comprobar nif
			// o nie
public class JugadorService {
	@Autowired
	private final JugadorRepository jugadorRepository;
	@Autowired
	private Validaciones validaciones;
	@Autowired
	private EquipoService equipoService;

	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	// -----------------------POST--------------------------
	public Jugador guardarJugador(Jugador jugador, String nombreEquipo) {
		// Buscar el ID del equipo por nombre
		Integer equipoID = equipoService.buscarIDPorNombre(nombreEquipo)
				.orElseThrow(() -> new EquipoNotFoundException("No se encuentra nombre de equipo: " + nombreEquipo));

		// Buscar el objeto Equipo usando el ID--
		// acaso
		Equipo equipo = equipoService.buscarPorId(equipoID)
				.orElseThrow(() -> new EquipoNotFoundException("No se encuentra equipo con ID: " + equipoID));

		// Establecer el objeto Equipo en el jugador
		jugador.setEquipo(equipo);

		String verDocument = jugador.getDocument();
		if (!validaciones.comprobarDNI(verDocument) && !validaciones.comprobarNIE(verDocument)) {
			throw new JugadorNotFoundException("Documento Inválido");
			// Recuerda que si no tienes excepciones personalizados, puedes usar esta
			// opcion:
			// new ResponseEntity<>("Documento inválido", HttpStatus.BAD_REQUEST);
		} else {
			Optional<Jugador> comprobarDocument = jugadorRepository.findByDocument(verDocument);
			if (comprobarDocument.isPresent()) {
				throw new JugadorBadRequest("NIF O NIE existe en la lista de los jugadores");

			} else {
				if (evitarDuplicarNombreCompleto(jugador.getNombre(), jugador.getPrimerapellido(),
						jugador.getSegundoapellido())) {
					throw new JugadorBadRequest("No se puede duplicar nombre completo");
				}
				return jugadorRepository.save(jugador);
			}

		}

	}

	/**
	 * Dejo este metodo por razon que un jugador puede estar jubilado o que
	 * simplemente esta libre
	 * 
	 * @param jugador
	 * @return
	 */
	public Jugador guardarJugadorSinEquipo(Jugador jugador) {
		String verDocument = jugador.getDocument();
		if (!validaciones.comprobarDNI(verDocument) && !validaciones.comprobarNIE(verDocument)) {
			throw new JugadorNotFoundException("Documento Inválido");
		} else {
			Optional<Jugador> comprobarDocument = jugadorRepository.findByDocument(verDocument);
			if (comprobarDocument.isPresent()) {
				throw new JugadorBadRequest("NIF O NIE existe en la lista de los jugadores");
			} else {
				if (evitarDuplicarNombreCompleto(jugador.getNombre(), jugador.getPrimerapellido(),
						jugador.getSegundoapellido())) {
					throw new JugadorBadRequest("No se puede duplicar nombre completo");
				}
				return jugadorRepository.save(jugador);
			}
		}
	}

	/**
	 * Es muy util para evitar que aparezca nombre completo duplicado.
	 * 
	 * @param nombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @return
	 */
	private boolean evitarDuplicarNombreCompleto(String nombre, String primerApellido, String segundoApellido) {
		Integer contador = jugadorRepository.contarJugadoresConNombreCompleto(nombre, primerApellido, segundoApellido);
		if (contador > 0) {
			return true;
		}
		return false;
	}

	// ----------------------------GET---------------------------
	public Optional<Jugador> buscarJugadorPorId(int id) {
		return jugadorRepository.findById(id);
	}

	public List<Jugador> buscarTodos() {
		return jugadorRepository.findAll();
	}

	public Optional<Jugador> buscarJugadorPorDocument(String document) {
		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		} else {
			Optional<Jugador> comprobarDocument = jugadorRepository.findByDocument(document);
			if (!comprobarDocument.isPresent()) {
				throw new JugadorNotFoundException(document + " no aparece la lista de los jugadores");
			}
			return jugadorRepository.findByDocument(document);
		}

	}

	public List<Jugador> buscarJugadoresPorNombre(String nombre) {
		List<Jugador> jugadores = jugadorRepository.findByNombre(nombre);
		for (Jugador jugador : jugadores) {
			if (jugador.getNombre().equalsIgnoreCase(nombre)) {
				return jugadorRepository.findByNombre(nombre);
			}
		}

		throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
	}

	/*
	 * // PROBAR SI FUNCIONA public List<Jugador> verJugadoresConEquipos() { return
	 * jugadorRepository.seeOnlyPlayersWithTeam(); }
	 */
	/// ------------------------------DELETE------------------
	public void eliminarJugador(int id) {
		jugadorRepository.deleteById(id);
	}

	public void eliminarJugadorv2(String document) {
		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		} else {
			Optional<Jugador> comprobarDocument = jugadorRepository.findByDocument(document);
			if (!comprobarDocument.isPresent()) {
				throw new JugadorNotFoundException(document + " no aparece la lista de los jugadores");
			}

			jugadorRepository.deleteByDocument(document);
		}

	}

	// ----------------------------------PUT-------------------
	/**
	 * Este metodo no tiene sentido por buscar id jugador
	 * 
	 * @param id
	 * @param jugadorActualizado
	 * @return
	 */
	public Jugador actualizarJugador(int id, Jugador jugadorActualizado) {
		Optional<Jugador> optionalJugador = jugadorRepository.findById(id);

		if (optionalJugador.isPresent()) {
			Jugador jugadorExiste = optionalJugador.get();
			jugadorExiste.setNombre(jugadorActualizado.getNombre());
			jugadorExiste.setEdad(jugadorActualizado.getEdad());
			jugadorExiste.setPosicion(jugadorActualizado.getPosicion());
			jugadorExiste.setPrimerapellido(jugadorActualizado.getPrimerapellido());
			jugadorExiste.setSegundoapellido(jugadorActualizado.getSegundoapellido());
			return jugadorRepository.save(jugadorExiste);
		} else {
			// Manejar el caso donde el equipo con el id dado no existe
			throw new RuntimeException("No se encontró el jugador con id: " + id);
		}
	}

	/**
	 * Esto si tiene sentido
	 * 
	 * @param document
	 * @param jugadorActualizado
	 * @return
	 */
	public Jugador actualizarJugadorV2(String document, Jugador jugadorActualizado) {
		Optional<Jugador> optionalJugador = jugadorRepository.findByDocument(document);
		if (optionalJugador.isPresent()) {
			Jugador jugadorExiste = optionalJugador.get();
			jugadorExiste.setNombre(jugadorActualizado.getNombre());
			jugadorExiste.setEdad(jugadorActualizado.getEdad());
			jugadorExiste.setPosicion(jugadorActualizado.getPosicion());
			jugadorExiste.setPrimerapellido(jugadorActualizado.getPrimerapellido());
			jugadorExiste.setSegundoapellido(jugadorActualizado.getSegundoapellido());
			return jugadorRepository.save(jugadorExiste);
		} else {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}
	}

	public Jugador cambiarEdad(String document, Jugador jugadorEdad) {
		Optional<Jugador> optionalJugador = jugadorRepository.findByDocument(document);
		if (optionalJugador.isPresent()) {
			Jugador jugadorExiste = optionalJugador.get();
			jugadorExiste.setEdad(jugadorEdad.getEdad());
			return jugadorRepository.save(jugadorExiste);
		} else {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}
	}
	//MODIFICAR
	public void cambiarEdadV2(String document, Integer edad) {
		Optional<Jugador> optionalJugador = jugadorRepository.findByDocument(document);
		if (optionalJugador.isPresent()) {
			   jugadorRepository.actualizarEdad(document, edad);
		} else {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}
	}
}
