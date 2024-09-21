package com.futbol.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.futbol.dto.EquipoDTO;
import com.futbol.dto.JugadorConEquipoDTO;
import com.futbol.dto.JugadorDTO;
import com.futbol.dto.JugadorUpdateDTO;
import com.futbol.dto.VincularEquipoDTO;
import com.futbol.model.Equipo;
import com.futbol.model.Jugador;
import com.futbol.service.JugadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugador", description = "Jugador API")
public class JugadorController {
	@Autowired
	private final JugadorService jugadorService;

	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	// -----------------------POST--------------------------
	/**
	 * 
	 * @param jugador
	 * @param nombreEquipo
	 * @return He averiguado como funciona la notacion de ResquestParam. Asi podria
	 *         funcionar de esta manera en vez introducir idequipo: por ejemplo:
	 *         http://localhost:8080/api/jugadores?nombreEquipo=Real Madrid FC
	 */
	@PostMapping
	public ResponseEntity<Jugador> crearJugador(@Valid @RequestBody Jugador jugador,
			@RequestParam String nombreEquipo) {
		Jugador nuevoJugador = jugadorService.guardarJugador(jugador, nombreEquipo);
		return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
	}

	// PROBAR SI FUNCIONA
	@PostMapping("/agregarJugadorSinEquipo")
	public ResponseEntity<Jugador> crearJugadorSinEquipo(@Valid @RequestBody Jugador jugador) {
		Jugador nuevoJugadorSinEquipo = jugadorService.guardarJugadorSinEquipo(jugador);
		return new ResponseEntity<>(nuevoJugadorSinEquipo, HttpStatus.CREATED);
	}

	// ----------------------------GET---------------------------
	@Operation(summary = "Buscar jugadores por ID", description = "Dado un ID, devuelve un objeto Jugador", tags = {
			"jugador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "jugador localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Jugador.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "jugador no encontrado (NO implementado)", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable("id") int id) {
		Optional<Jugador> jugador = jugadorService.buscarJugadorPorId(id);
		return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	//@GetMapping("/document/{document}")
	/**
	 * Hay tres metodos diferentes ejemplos para usar:
	 * -buscarJugadorPorDocument: es un metodo simple
	 * -buscarJugadorPorDocumentV2: Es un metodo que acabo de utilizar orElseThrow para Optional
	 * -buscarJugadorPorDocumentV3: Es un metodo que acabo de utilizar orElseThrow pero para clase
	 * @param document
	 * @return
	 */
	public ResponseEntity<Jugador> obtenerJugadorPorDocument(@PathVariable("document") String document) {
		Optional<Jugador> jugador = jugadorService.buscarJugadorPorDocumentV3(document);
		return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	/**
	 * Este metodo es muy especifico, mi intención es que solo aparezca nombre completo de un jugador
	 * gracias a una clase de DTO y además también estoy usando mapper. El metodo principal desde servicio es
	 * buscarJugadorPorDocumenMapperToDTO
	 * buscarJugadorPorDocumenToDTO -> No funciona.
	 * 
	 * @param nombre
	 * @return
	 */
	@GetMapping("/document/{document}")
	public ResponseEntity<JugadorDTO> ObtenerJugadorPorDocumentDTO(@PathVariable("document") String document) {
		JugadorDTO jugador =jugadorService.buscarJugadorPorDocumenMapperToDTO(document);
		return ResponseEntity.ok(jugador);
	}
	

	/**
	 * Esto es un una consulta con entidad sin o con mapeo 
	 * -Metodo: buscarJugadoresPorNombre (No hace la consulta personalizada) 
	 * -Metodo: buscarJugadoresPorNombreEntityMapper
	 * 
	 * @param nombre
	 * @return
	 */
	//@GetMapping("/verJugadoresPorNombre/{nombre}")
	public ResponseEntity<List<Jugador>> verJugadoresPorNombre(@PathVariable("nombre") String nombre) {
		List<Jugador> jugadores = jugadorService.buscarJugadoresPorNombreEntityMapper(nombre);
		return ResponseEntity.ok(jugadores);
	}

	/**
	 * Esto es una consulta personalizada con DTO sin o con mapeo 
	 * - metodo: buscarJugadoresPorNombreDTO (Algo no va bien) 
	 * - metodo:buscarJugadoresPorNombreDTOMapper
	 * 
	 * @param nombre
	 * @return
	 */
	@GetMapping("/verJugadoresPorNombre/{nombre}")
	public ResponseEntity<List<JugadorDTO>> verJugadoresPorNombreDTO(@PathVariable("nombre") String nombre) {
		List<JugadorDTO> jugadores = jugadorService.buscarJugadoresPorNombreDTO(nombre);
		return ResponseEntity.ok(jugadores);
	}
	/**
	 * 
	 * Solo tengo el metodo de buscarJugadoresPorNombreString
	 * @param nombre
	 * @return
	 */
	@GetMapping("/verJugadoresPorNombreString/{nombre}")
	public ResponseEntity<List<String>> verJugadoresPorNombreString(@PathVariable("nombre") String nombre) {
		List<String> jugadores = jugadorService.buscarJugadoresPorNombreString(nombre);
		return ResponseEntity.ok(jugadores);
	}

	/**
	 * 
	 * Consulta personalizada por mapper de una entidad
	 * @param posicion
	 * @return
	 */
//	@GetMapping("/verJugadoresConEquipos/{posicion}")
	public ResponseEntity<List<Jugador>> verJugadoresConEquiposPorPosicion(@PathVariable("posicion") String posicion) {
		List<Jugador> jugadores = jugadorService.verJugadoresConEquipoPorPosicionEntityMapper(posicion);
		return ResponseEntity.ok(jugadores);
	}
	/**
	 * Consulta personalizad por mapper de un dto
	 * @param posicion
	 * @return
	 */
	@GetMapping("/verJugadoresConEquipos/{posicion}")
	public ResponseEntity<List<JugadorConEquipoDTO>> verJugadoresConEquiposPorPosicionDTO(
			@PathVariable("posicion") String posicion) {
		List<JugadorConEquipoDTO> jugadores = jugadorService.verJugadoresConEquipoPorPosicionDTOMapper(posicion);
		return ResponseEntity.ok(jugadores);
	}
	
	@GetMapping("/posicionJugadores/{agregarPosicion}")
	public ResponseEntity<List<Jugador>> verJugadoresPorPosicion(@PathVariable("agregarPosicion") String posicion) {
		List<Jugador> jugadores = jugadorService.verJugadoresPorPosicion(posicion);
		return ResponseEntity.ok(jugadores);
	}
	
	
	@GetMapping("/posicionJugadoresString/{agregarPosicion}")
	public ResponseEntity<List<String>> verJugadoresPorPosicionString(@PathVariable("agregarPosicion") String posicion){
		List <String> jugadores = jugadorService.buscarJugadoresPorPosicionString(posicion);
		return ResponseEntity.ok(jugadores);
	}

	@GetMapping("/verTodosJugadores")
	public ResponseEntity<List<Jugador>> obtenerTodosLosJugadores() {
		List<Jugador> jugadores = jugadorService.buscarTodos();
		return ResponseEntity.ok(jugadores);
	}

	// ----------------------------------PUT-------------------
	/**
	 * Este metodo es una version original y no veo logico
	 * 
	 * @param id
	 * @param jugadorActualizado
	 * @return
	 */
	@PutMapping("/modificarJugador/{id}")
	public ResponseEntity<Jugador> actualizarJugador(@PathVariable("id") int id,
			@RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.actualizarJugador(id, jugadorActualizado);
		return ResponseEntity.ok(jugador);
	}

	/**
	 * Este en cambio si veo logico, en vez de buscar por id, se encuentra por
	 * document que puede ser NIF o NIE antes de actualizar.
	 * 
	 * @param document
	 * @param jugadorActualizado
	 * @return
	 */
	@PutMapping("/modificarJugadorV2/{document}")
	public ResponseEntity<Jugador> actualizarJugadorv2(@PathVariable("document") String document,
			@Valid @RequestBody JugadorUpdateDTO jugadorActualizado) {
		Jugador jugador = jugadorService.actualizarJugadorV2(document, jugadorActualizado);
		return ResponseEntity.ok(jugador);
	}

	@PutMapping("/cambiarEdad/{document}")
	public ResponseEntity<Jugador> cambiarEdad(@PathVariable("document") String document,
			@RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.cambiarEdad(document, jugadorActualizado);
		return ResponseEntity.ok(jugador);

	}

	// NO FUNCIONA CORRECTAMENTE
	@PutMapping("/cambiarEdad2/{document}")
	public ResponseEntity<String> cambiarEdad2(@PathVariable String document,
			@RequestParam(name = "modificarEdad") Integer edad) {
		jugadorService.cambiarEdadV2(document, edad);
		return ResponseEntity.ok("Edad actualizada correctamente");
	}

	// FUNCIONA BIEN CON DTO
	@PutMapping("/cambiarEdadDTO/{document}")
	public ResponseEntity<String> cambiarEdadDTO(@PathVariable String document,
			@Valid @RequestBody JugadorDTO request) {
		jugadorService.cambiarEdadV2(document, request.getEdad());
		return ResponseEntity.ok("Edad actualizada correctamente");
	}

	
	@PutMapping("/desvincularEquipo/{document}")
	public ResponseEntity<String> desvincularEquipo(@PathVariable String document) {
		jugadorService.desvincularEquipo(document);
		return ResponseEntity.ok("Ha sido desvinculado correctamente");
	}

	@PutMapping("/vincularEquipo/{document}")
	public ResponseEntity<String> vincularEquipoV2(@PathVariable("document") String document,
			@RequestBody VincularEquipoDTO vincularEquipo) {
		jugadorService.vincularEquipo(document, vincularEquipo);
		return ResponseEntity.ok("Ha sido vinculado correctamente");
	}

	/// ------------------------------DELETE------------------
	@DeleteMapping("/eliminarJugadorPorID/{id}")
	public ResponseEntity<Void> eliminarJugador(@PathVariable("id") int id) {
		jugadorService.eliminarJugador(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/eliminarJugadorPorDocument/{document}")
	public ResponseEntity<Void> eliminarJugadorV2(@PathVariable("document") String document) {
		jugadorService.eliminarJugadorv2(document);
		return ResponseEntity.noContent().build();

	}

}
