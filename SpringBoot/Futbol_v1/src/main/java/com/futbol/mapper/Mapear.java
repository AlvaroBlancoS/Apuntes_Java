package com.futbol.mapper;

import com.futbol.dto.JugadorConEquipoDTO;
import com.futbol.dto.JugadorDTO;
import com.futbol.model.Equipo;
import com.futbol.model.Jugador;

/**
 * Es una tecnica para mappear resultados de consultas a un formato especifico,
 * como un DTO o un tipo de coleccion
 */
public class Mapear {

	/**
	 * 
	 * @param jugador
	 * @return Convierte en una entidad Jugador (entity) en un DTO JugadorDTO
	 */
	public JugadorDTO toDtoVerJugadorYposicion(Jugador jugador) {
		if (jugador == null) {
			return null;
		}
		JugadorDTO dto = new JugadorDTO();
		dto.setNombre(jugador.getNombre());
		dto.setPrimerapellido(jugador.getPrimerapellido());
		dto.setSegundoapellido(jugador.getSegundoapellido());
		dto.setPosicion(jugador.getPosicion());
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return convierte un dto JugadorDTO en una entidad (entity) Jugador
	 */
	public Jugador toEntityJugadorYposicion(JugadorDTO dto) {
		if (dto == null) {
			return null;
		}
		Jugador entity = new Jugador();
		entity.setNombre(dto.getNombre());
		entity.setPrimerapellido(dto.getPrimerapellido());
		entity.setSegundoapellido(dto.getSegundoapellido());
		entity.setPosicion(dto.getPosicion());
		return entity;
	}

	// -----------------------------DOS METODOS QUE SOLO APARECEN JUGADORES CON
	// EQUIPOS POR LA POSICION
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public JugadorConEquipoDTO toDtoVerJugadoresConEquipoPorPosicion(Jugador entity) {
		if (entity == null) {
			return null;
		}
		JugadorConEquipoDTO dto = new JugadorConEquipoDTO();
		dto.setNombre(entity.getNombre());
		dto.setPrimerapellido(entity.getPrimerapellido());
		dto.setSegundoapellido(entity.getSegundoapellido());

		if (entity.getEquipo() != null) {
			dto.setNombreEquipo(entity.getEquipo().getNombreEquipo());
		}
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public Jugador toEntityVerJugadoresConEquipoPorPosicion(JugadorConEquipoDTO dto) {
		if (dto == null) {
			return null;
		}
		Jugador j = new Jugador();
		j.setNombre(dto.getNombre());
		j.setPrimerapellido(dto.getPrimerapellido());
		j.setSegundoapellido(dto.getSegundoapellido());

		if (dto.getNombreEquipo() != null) {
			Equipo equipo = new Equipo();
			equipo.setNombreEquipo(dto.getNombreEquipo());
			j.setEquipo(equipo);
		}
		return j;
	}
	
	// -----------------------------Ver un jugador o jugadores que solo aparezcan nombres completos
	
	public JugadorDTO toDTOverJugador(Jugador j) {
		if (j==null) {
			return null;
		}
		JugadorDTO jugadorDTO = new JugadorDTO();
		jugadorDTO.setNombre(j.getNombre());
		jugadorDTO.setPrimerapellido(j.getPrimerapellido());
		jugadorDTO.setSegundoapellido(j.getSegundoapellido());
		 return jugadorDTO;
	}
	
	public Jugador toEntityverJugador(JugadorDTO dto) {
		if (dto==null) {
			
		}
		
		Jugador jugador = new Jugador();
		jugador.setNombre(dto.getNombre());
		jugador.setPrimerapellido(dto.getPrimerapellido());
		jugador.setSegundoapellido(dto.getSegundoapellido());
		
		return jugador;
	}

}
