package com.futbol.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.futbol.model.Jugador;
import com.futbol.service.JugadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugador", description = "Jugador API")
public class JugadorController {

	private final JugadorService jugadorService;

	@Autowired
	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	@PostMapping
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
		Jugador nuevoJugador = jugadorService.guardarJugador(jugador);
		return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
	}

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

	@GetMapping
	public ResponseEntity<List<Jugador>> obtenerTodosLosJugadores() {
		List<Jugador> jugadores = jugadorService.buscarTodos();
		return ResponseEntity.ok(jugadores);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Jugador> actualizarJugador(@PathVariable("id") int id,
			@RequestBody Jugador jugadorActualizado) {
		Jugador jugador = jugadorService.actualizarJugador(id, jugadorActualizado);
		return ResponseEntity.ok(jugador);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarJugador(@PathVariable("id") int id) {
		jugadorService.eliminarJugador(id);
		return ResponseEntity.noContent().build();
	}

}
