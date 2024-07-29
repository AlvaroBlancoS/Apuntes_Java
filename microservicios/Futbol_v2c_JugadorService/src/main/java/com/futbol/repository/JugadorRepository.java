package com.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futbol.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
	
	//No funciona y no consigo entender con la funcion DTO.
	/*
	@Query("SELECT new com.futbol.model.JugadorDTO(a.nombre, a.primerapellido, a.segundoapellido, a.edad, a.posicion, e.nombre) " +
		       "FROM Jugador a INNER JOIN a.equipo e")
		List<JugadorDTO> listJugadoresConEquipos();
		
		*/
	@Query("SELECT a FROM Jugador a JOIN FETCH a.equipo")
	List<Jugador> listJugadoresConEquiposV2();
	@Query("SELECT a FROM Jugador a")
	List<Jugador>listaJugadoresSinEquipos();
}
