package com.futbol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbol.model.Jugador;
import com.futbol.repository.JugadorRepository;

@Service
public class JugadorService {

	private final JugadorRepository jugadorRepository;

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	public Jugador guardarJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	public Optional<Jugador> buscarJugadorPorId(int id) {
		return jugadorRepository.findById(id);
	}

	public List<Jugador> buscarTodos() {
		return jugadorRepository.findAll();
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
        	jugadorExiste.setPrimerapellido(jugadorExiste.getPrimerapellido());
        	jugadorExiste.setSegundoapellido(jugadorActualizado.getSegundoapellido());
            return jugadorRepository.save(jugadorExiste);
        } else {
            // Manejar el caso donde el equipo con el id dado no existe
            throw new RuntimeException("No se encontró el jugador con id: " + id);
        }
    }
}
