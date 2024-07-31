package com.futbol.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	// PROBAR SIN FUNCIONA
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

	@GetMapping("/verTodosJugadores")
	public ResponseEntity<List<Jugador>> obtenerTodosLosJugadores() {
		List<Jugador> jugadores = jugadorService.buscarTodos();
		return ResponseEntity.ok(jugadores);
	}

	@GetMapping("/document/{document}")
	public ResponseEntity<Jugador> obtenerJugadorPorDocument(@PathVariable("document") String document) {
		Optional<Jugador> jugador = jugadorService.buscarJugadorPorDocument(document);
		return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/verJugadoresPorNombre/{nombre}")
	public ResponseEntity<List<Jugador>> verJugadoresPorNombre(@PathVariable("nombre") String nombre) {
		List<Jugador> jugadores = jugadorService.buscarJugadoresPorNombre(nombre);
		return ResponseEntity.ok(jugadores);
	}

	// ----------------------------------PUT-------------------

	@PutMapping("/modificarJugador/{id}")
	public ResponseEntity<Jugador> actualizarJugador(@PathVariable("id") int id,
			@RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.actualizarJugador(id, jugadorActualizado);
		return ResponseEntity.ok(jugador);
	}

	@PutMapping("/modificarJugadorV2/{document}")
	public ResponseEntity<Jugador> actualizarJugadorv2(@PathVariable("document") String document,
			@Valid @RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.actualizarJugadorV2(document, jugadorActualizado);
		return ResponseEntity.ok(jugador);
	}

	@PutMapping("/cambiarEdad/{document}")
	public ResponseEntity<Jugador> cambiarEdad(@PathVariable("document") String document,
			@RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.cambiarEdad(document, jugadorActualizado);
		return ResponseEntity.ok(jugador);

	}
	//MODIFICAR
	@PutMapping("/cambiarEdad2/{document}")
	public ResponseEntity<String> cambiarEdad2(@PathVariable("document") String document, @RequestParam(name = "edad") Integer edad) {
		jugadorService.cambiarEdadV2(document, edad);
		return ResponseEntity.ok("Edad actualizada correctamente");

	}

	/// ------------------------------Delete------------------
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
