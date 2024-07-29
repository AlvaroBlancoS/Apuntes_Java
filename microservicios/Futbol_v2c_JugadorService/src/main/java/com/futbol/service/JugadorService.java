package com.futbol.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbol.DTO.JugadorDTO;
import com.futbol.model.Equipo;
import com.futbol.model.Jugador;
import com.futbol.repository.JugadorRepository;
//import com.futbol.util.EquipoNotFoundException;
import com.futbol.util.JugadorNotFoundException;

@Service
public class JugadorService {

	@Autowired
	private final JugadorRepository jugadorRepository;

	// @Autowired
	// private RestTemplate restTemplate;

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	public Jugador asignarEquipo(int jugadorId, int equipoId) {
		// Buscar el jugador por ID utilizando Optional
		Optional<Jugador> optionalJugador = jugadorRepository.findById(jugadorId);
		if (!optionalJugador.isPresent()) {
			throw new JugadorNotFoundException("Jugador no encontrado");
		}

		// Comunicación REST para obtener información del equipo desde EquipoService
		// Equipo equipo = restTemplate.getForObject("http://equipo-service/equipos/" +
		// equipoId, Equipo.class);
		Equipo equipo = new Equipo();

		if (equipo.getIdequipo() == null) {
			//throw new EquipoNotFoundException("Equipo no encontrado");
		}

		// Asignar el equipo al jugador y guardar
		Jugador jugador = optionalJugador.get();
		// jugador.setEquipo(equipo);
		return jugadorRepository.save(jugador);
	}

	public Jugador guardarJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	public Optional<Jugador> buscarJugadorPorId(int id) {
		return jugadorRepository.findById(id);
	}

	public List<JugadorDTO> listaDeJugadoresSinEquipos() {
		List<Jugador> jugadores = jugadorRepository.listaJugadoresSinEquipos();
		return jugadores.stream().map(j -> 
		new JugadorDTO(j.getNombre(), 
				j.getPrimerapellido(), 
				j.getSegundoapellido(),
				j.getEdad(), 
				j.getPosicion(),
				j.getEquipo().getNombre() != null ? null : null
				)).collect(Collectors.toList());
	}
/*
	public List<JugadorDTO> listaDeJugadoresConEquipos() {
		return jugadorRepository.listJugadoresConEquipos();
	}
*/
	public List<JugadorDTO> listaDeJugadoresConEquiposv2() {
		List<Jugador> jugadores = jugadorRepository.listJugadoresConEquiposV2();
		return jugadores.stream()
				.map(jugador -> new JugadorDTO(jugador.getNombre(), jugador.getPrimerapellido(),
						jugador.getSegundoapellido(), jugador.getEdad(), jugador.getPosicion(),
						jugador.getEquipo().getNombre()))
				.collect(Collectors.toList());
	}

	public void eliminarJugador(int id) {
		jugadorRepository.deleteById(id);
	}

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
}
