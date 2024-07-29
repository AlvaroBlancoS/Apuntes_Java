package com.futbol.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futbol.DTO.JugadorDTO;
import com.futbol.model.Jugador;
import com.futbol.service.JugadorService;


@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
	@Autowired
	private final JugadorService jugadorService;

	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	@PostMapping
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
		Jugador nuevoJugador = jugadorService.guardarJugador(jugador);
		return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
	}

	@GetMapping("/listarJugadorescConEquipos")
	public List<JugadorDTO> listaJugadoresConEquipos() {
		return jugadorService.listaDeJugadoresConEquiposv2();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable("id") int id) {
		Optional<Jugador> jugador = jugadorService.buscarJugadorPorId(id);
		return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

    @GetMapping("/listarJugadorescSinEquipos")
    public ResponseEntity<List<JugadorDTO>> obtenerTodosLosJugadores() {
        List<JugadorDTO> jugadores = jugadorService.listaDeJugadoresSinEquipos();
        return ResponseEntity.ok(jugadores);
    }
    /*
	public List<JugadorDTO> obtenerTodosLosJugadores() {
		return jugadorService.listaDeJugadoresSinEquipos();
	}
*/
	@PutMapping("/{jugadorId}/equipo/{equipoId}")
	public ResponseEntity<Jugador> asignarEquipo(@PathVariable int jugadorId, @PathVariable int equipoId) {
		Jugador jugadorActualizado = jugadorService.asignarEquipo(jugadorId, equipoId);
		return ResponseEntity.ok(jugadorActualizado);
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
