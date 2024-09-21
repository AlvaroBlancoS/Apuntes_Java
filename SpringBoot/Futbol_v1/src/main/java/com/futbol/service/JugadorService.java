package com.futbol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.futbol.dto.JugadorConEquipoDTO;
import com.futbol.dto.JugadorDTO;
import com.futbol.dto.JugadorUpdateDTO;
import com.futbol.dto.VincularEquipoDTO;
import com.futbol.mapper.Mapear;
import com.futbol.model.Equipo;
import com.futbol.model.Jugador;
import com.futbol.repository.JugadorRepository;
import com.futbol.util.EquipoNotFoundException;
import com.futbol.util.JugadorBadRequest;
import com.futbol.util.JugadorNotFoundException;
import com.futbol.util.Validaciones;

import jakarta.transaction.Transactional;

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
	/**
	 * DEBO PROBAR DE NUEVO POR SI ACASO
	 * @param jugador
	 * @param nombreEquipo
	 * @return
	 */
	public Jugador guardarJugador(Jugador jugador, String nombreEquipo) {
		// Buscar el ID del equipo por nombre
		Integer equipoID = equipoService.buscarIDPorNombre(nombreEquipo)
				.orElseThrow(() -> new EquipoNotFoundException("No se encuentra nombre de equipo: " + nombreEquipo));

//		if (equipoID ==null) {
//			new EquipoNotFoundException("No se encuentra nombre de equipo: " + nombreEquipo);
//		}

		// Buscar el objeto Equipo usando el ID--
		// acaso
		Equipo equipo = equipoService.buscarPorId(equipoID)
				.orElseThrow(() -> new EquipoNotFoundException("No se encuentra equipo con ID: " + equipoID));

//		if (equipo == null) {
//			new EquipoNotFoundException("No se encuentra equipo con ID: " + equipoID);
//		}

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

	// ----------------------------GET---------------------------

	/** 
	 * @param id
	 * @return
	 */
	public Optional<Jugador> buscarJugadorPorId(int id) throws JugadorNotFoundException {
//		Optional<Jugador> jugadorPorId = jugadorRepository.findById(id);
//		if (!jugadorPorId.isPresent()) {
//			throw new JugadorNotFoundException(id + " no aparece en la lista de los jugadores");
//		}
//		jugadorRepository.findById(id).orElseThrow(JugadorNotFoundException::new);
//		jugadorRepository.findById(id).orElseThrow(()-> new JugadorNotFoundException(id+" no aparece en la lista de los jugadores"));

		return Optional.of(jugadorRepository.findById(id)
				.orElseThrow(() -> new JugadorNotFoundException(id + " no aparece en la lista de los jugadores")));
	}

	public Optional<Jugador> buscarJugadorPorDocument(String document) throws JugadorNotFoundException {
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

	public Optional<Jugador> buscarJugadorPorDocumentV2(String document) throws JugadorNotFoundException {
		Optional<Jugador> jugadorPorDocument = Optional.of(jugadorRepository.findByDocument(document)
				.orElseThrow(() -> new EquipoNotFoundException(document + " no aparece en la lista")));
		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		}
		return jugadorPorDocument;
	}

	public Optional<Jugador> buscarJugadorPorDocumentV3(String document) throws JugadorNotFoundException {
		Jugador jugadorPorDocument = jugadorRepository.findByDocument(document)
				.orElseThrow(() -> new EquipoNotFoundException(document + " no aparece en la lista"));
		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		}
		Optional<Jugador> documentOptional = Optional.of(jugadorPorDocument);
		return documentOptional;
	}

	public JugadorDTO buscarJugadorPorDocumenMapperToDTO(String document) throws JugadorNotFoundException {
		Jugador jugadorEntity = jugadorRepository.findByDocument(document)
				.orElseThrow(() -> new EquipoNotFoundException(document + " no aparece en la lista"));

		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		}
		return new Mapear().toDTOverJugador(jugadorEntity);
	}

	/**
	 * No funciona correctamente
	 * 
	 * @param document
	 * @return
	 */
	public JugadorDTO buscarJugadorPorDocumenToDTO(String document) throws JugadorNotFoundException {
		Jugador j = jugadorRepository.findByDocument(document)
				.orElseThrow(() -> new EquipoNotFoundException(document + " no aparece en la lista"));

		if (!validaciones.comprobarDNI(document) && !validaciones.comprobarNIE(document)) {
			throw new JugadorNotFoundException("Documento Inválido");
		}
		return new JugadorDTO(j.getNombre(), j.getPrimerapellido(), j.getSegundoapellido(), j.getPosicion());
	}

	/**
	 * Es una consulta básica sin personalizar Se trata de buscar una lista de
	 * jugadores por un nombre Puede aparecer dos o tres jugadores que se llaman
	 * 'Pepito'
	 * 
	 * @param nombre
	 * @return
	 */
	public List<Jugador> buscarJugadoresPorNombre(String nombre) throws JugadorNotFoundException {
		List<Jugador> jugadores = jugadorRepository.findByNombre(nombre);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
		}
		return jugadores;

	}

	/**
	 * Es similar que el metodo de buscarJugadoresPorNombre, pero: Hace una consulta
	 * personalizada con mapeo de entidad
	 * 
	 * @param nombre
	 * @return
	 */
	public List<Jugador> buscarJugadoresPorNombreEntityMapper(String nombre) throws JugadorNotFoundException {
		List<JugadorDTO> jugadores = jugadorRepository.findByNombreDTO(nombre);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
		}
		return jugadores.stream().map(new Mapear()::toEntityJugadorYposicion).collect(Collectors.toList());

	}

	/**
	 * Hace consulta personalizada con mapeo de DTO
	 * 
	 * @param nombre
	 * @return
	 */
	public List<JugadorDTO> buscarJugadoresPorNombreDTOMapper(String nombre) throws JugadorNotFoundException {
		List<Jugador> jugadores = jugadorRepository.findByNombre(nombre);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
		}
		return jugadores.stream().map(new Mapear()::toDtoVerJugadorYposicion).collect(Collectors.toList());

	}

	/**
	 * Hace una consulta personalizad solo con DTO ERROR: hay algunos campos que no
	 * le devuelven
	 * 
	 * @param nombre
	 * @return
	 */
	public List<JugadorDTO> buscarJugadoresPorNombreDTO(String nombre) throws JugadorNotFoundException {
		List<JugadorDTO> jugadores = jugadorRepository.findByNombreDTO(nombre);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
		}
		return jugadores.stream()
				.map(j -> new JugadorDTO(j.getNombre(), j.getPrimerapellido(), j.getSegundoapellido(), j.getPosicion()))
				.collect(Collectors.toList());
	}

	/**
	 * Es una lista, esto quiere decir que si aparecen dos jugadores que se llaman Juanito
	 * apareceran en la lista.
	 * 
	 * @param nombre
	 * @return
	 * @throws JugadorNotFoundException
	 */
	public List<String> buscarJugadoresPorNombreString(String nombre) throws JugadorNotFoundException {
		List<Jugador> jugadores = jugadorRepository.findByNombre(nombre);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(nombre + " no aparece en la lista de los jugadores");
		}

		return jugadores.stream()
				.map(j -> j.getNombre() + " " + j.getPrimerapellido() + " " + j.getEquipo().getNombreEquipo())
				.collect(Collectors.toList());
	}

	/**
	 * Este metodo hace la consulta de buscar jugadores por la posicion del campo
	 * pero solo con equipos. El primer metodo que estoy usando desde respositorio
	 * es findByPositionDTO. Sin embargo, el metodo findByPositionDTOv2 se puede
	 * encontrar jugadores con y sin equipos por posicion
	 * 
	 * @param posicion
	 * @return
	 */
	public List<Jugador> verJugadoresConEquipoPorPosicionEntityMapper(String posicion) throws JugadorNotFoundException {
		List<JugadorConEquipoDTO> jugadoresConEquipos = jugadorRepository.findByPositionDTO(posicion);
		if (jugadoresConEquipos.isEmpty()) {
			throw new JugadorNotFoundException("No se encontraron jugadores con equipo en la posición: " + posicion);
		}

		List<Jugador> jugadores = jugadoresConEquipos.stream()
				.map(new Mapear()::toEntityVerJugadoresConEquipoPorPosicion).collect(Collectors.toList());

		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(posicion + " no aparece en la lista de los jugadores");
		}

		return jugadores;

//		return jugadoresConEquipos.stream().map(new Mapear()::toEntityVerJugadoresConEquipoPorPosicion)
//				.collect(Collectors.toList());
	}

	/**
	 * Con la entidad se puede buscar jugadores por posicion con y sin equipos. Hay
	 * dos metodos que se puede usar: -findByPositionEntity es una consulta
	 * personalizada con la Query -findByPositionEntityv2 es una consulta sin
	 * personalizar
	 * 
	 * @param posicion
	 * @return devuelve una lista de los jugadores
	 */
	public List<Jugador> verJugadoresPorPosicion(String posicion) {
		return jugadorRepository.findByPositionEntity(posicion);
	}

	/**
	 * 
	 * Este metodo solo aparecera el nombre de los jugadores por la posicion Es muy
	 * util si quieres jugar los metodos getters.
	 * 
	 * @param posicion
	 * @return devuelve una lista de jugadores en String
	 */
	public List<String> buscarJugadoresPorPosicionString(String posicion) throws JugadorNotFoundException {
		List<Jugador> jugadores = jugadorRepository.findByPositionEntity(posicion);
		if (jugadores.isEmpty()) {
			new JugadorNotFoundException("No aparece ninguna lista de la " + posicion);
		}
		return jugadores.stream().map(Jugador::getNombre).collect(Collectors.toList());
	}

	/**
	 * 
	 * Sin embargo, el objetivo es ver jugador solo con equipos
	 * -findByPositionEntity -findByPosicion Creo que para mi es mas recomendable
	 * usar este metodo
	 * 
	 * @param posicion
	 * @return
	 */
	public List<JugadorConEquipoDTO> verJugadoresConEquipoPorPosicionDTOMapper(String posicion) {
		List<Jugador> jugadores = jugadorRepository.findByPosicion(posicion);
		if (jugadores.isEmpty()) {
			throw new JugadorNotFoundException(posicion + " no aparece en la lista de los jugadores");
		}
		// Filtra los jugadores que tienen un equipoç
		/*
		 * Si quitas filter, podras ver jugadores por posicion que estan con equipos y
		 * sin equipos
		 */
		List<JugadorConEquipoDTO> jugadoresConEquipo = jugadores.stream().filter(j -> j.getEquipo() != null)
				.map(new Mapear()::toDtoVerJugadoresConEquipoPorPosicion) // Mapea a DTO
				.collect(Collectors.toList());

		if (jugadoresConEquipo.isEmpty()) {
			throw new JugadorNotFoundException("No se encontraron jugadores con equipo en la posición: " + posicion);
		}

		return jugadoresConEquipo;
		// La otra opcion es devolver directamente los jugadores con equipos.
		// return jugadores.stream().map(new
		// Mapear()::toDtoVerJugadoresConEquipoPorPosicion).collect(Collectors.toList());
	}

	public List<Jugador> buscarTodos() {
		return jugadorRepository.findAll();
	}

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
			// throw new RuntimeException("No se encontró el jugador con id: " + id);
			throw new JugadorNotFoundException("No se encontró el jugador id: " + id);
		}
	}

	/**
	 * Es necesario usar JugadorUpadteDTO porque el campo document me obliga a
	 * modificar o actualizar si o si. Es un campo obligatorio
	 * 
	 * @param document
	 * @param jugadorActualizado
	 * @return
	 */
	public Jugador actualizarJugadorV2(String document, JugadorUpdateDTO jugadorActualizado) {
		Optional<Jugador> jugadorExistente = Optional.of(jugadorRepository.findByDocument(document).orElseThrow(
				() -> new JugadorNotFoundException(document + " no aparece en la lista de los jugadores")));

		String verDocument = document;
		if (!validaciones.comprobarDNI(verDocument) && !validaciones.comprobarNIE(verDocument)) {
			throw new JugadorNotFoundException("Documento Inválido");
		} else {
			if (evitarDuplicarNombreCompleto(jugadorActualizado.getNombre(), jugadorActualizado.getPrimerapellido(),
					jugadorActualizado.getSegundoapellido())) {
				throw new JugadorBadRequest("No se puede duplicar nombre completo");
			}

		}
		Jugador jugadorExiste = jugadorExistente.get();
		jugadorExiste.setNombre(jugadorActualizado.getNombre());
		jugadorExiste.setEdad(jugadorActualizado.getEdad());
		jugadorExiste.setPosicion(jugadorActualizado.getPosicion());
		jugadorExiste.setPrimerapellido(jugadorActualizado.getPrimerapellido());
		jugadorExiste.setSegundoapellido(jugadorActualizado.getSegundoapellido());
		return jugadorRepository.save(jugadorExiste);
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

	@Transactional
	public void cambiarEdadV2(String document, Integer edad) {
		Optional<Jugador> optionalJugador = jugadorRepository.findByDocument(document);
		if (optionalJugador.isPresent()) {
			jugadorRepository.actualizarEdad(document, edad);
		} else {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}
	}

	public Jugador desvincularEquipo(String document) {
		Optional<Jugador> jugadorOptional = jugadorRepository.findByDocument(document);
		if (!jugadorOptional.isPresent()) {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}

		Jugador jugador = jugadorOptional.get();

		if (jugador.getEquipo() == null) {
			throw new JugadorBadRequest("El jugador ya no tiene equipo vinculado.");
		}

		jugador.setEquipo(null);

		return jugadorRepository.save(jugador);
	}

	public Jugador vincularEquipo(String document, VincularEquipoDTO vincularEquipo) {
		Optional<Jugador> jugador = jugadorRepository.findByDocument(document);
		if (!jugador.isPresent()) {
			throw new JugadorNotFoundException(document + " no aparece en la lista de los jugadores");
		}

		Jugador vincular = jugador.get();

		System.err.println("Esto es el nombre de equipo " + vincularEquipo.getNombreEquipo());

		// Buscar el ID equipo por su nombre
		Integer equipoID = equipoService.buscarIDPorNombre(vincularEquipo.getNombreEquipo())
				.orElseThrow(() -> new EquipoNotFoundException(
						"No se encuentra nombre de equipo: " + vincularEquipo.getNombreEquipo()));

		System.err.println("Este es el id equipo " + equipoID);

		// Buscar nombre de equipo por ID
		Equipo equipo = equipoService.buscarPorId(equipoID)
				.orElseThrow(() -> new EquipoNotFoundException("No se encuentra equipo con ID: " + equipoID));

		vincular.setEquipo(equipo);

		return jugadorRepository.save(vincular);
	}

	/**
	 * Es extranio para evitar que aparezca nombre completo duplicado. Ya que es un
	 * poco habitual que exista dos personas que tienen nombre completo.
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
}
