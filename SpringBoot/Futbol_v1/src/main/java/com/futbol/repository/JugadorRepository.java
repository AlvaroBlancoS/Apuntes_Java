package com.futbol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.futbol.dto.JugadorConEquipoDTO;
import com.futbol.dto.JugadorDTO;
import com.futbol.model.Jugador;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
	final String jugadorDTO = "new com.futbol.dto.JugadorConEquipoDTO(j.nombre, j.primerapellido, j.segundoapellido, e.nombreEquipo) ";
	final String jugadorConEquipoDTO="SELECT new com.futbol.dto.JugadorConEquipoDTO(j.nombre, j.primerapellido, j.segundoapellido, e.nombreEquipo) ";
	final String jugadorConEquipoEntity="";

	// ESTOS METODOS SON LAS OPCIONES DE BUSCAR JUGADORES POR NOMBRES
	List<Jugador> findByNombre(String nombre);

	@Query("SELECT new com.futbol.dto.JugadorDTO(j.nombre, j.primerapellido, j.segundoapellido, j.posicion) FROM Jugador j WHERE j.nombre = :buscarNombre")
	List<JugadorDTO> findByNombreDTO(@Param("buscarNombre") String nombre);
	// -----------------------------------------------
	// ------ESTAS SON LAS OPCIONES DE BUSCAR JUGADORES POR POSICION-----

	@Query("SELECT new com.futbol.dto.JugadorConEquipoDTO(j.nombre, j.primerapellido, j.segundoapellido, e.nombreEquipo) "
			+ "FROM Jugador j  JOIN j.equipo e WHERE j.posicion = :buscarPosicion")
	/**
	 * Se busca la posicion de la jugadores solo con equipos usando DTO de una lista
	 * 
	 * @param posicion
	 * @return
	 */
	List<JugadorConEquipoDTO> findByPositionDTO(@Param("buscarPosicion") String posicion);

	@Query("SELECT new com.futbol.dto.JugadorConEquipoDTO(j.nombre, j.primerapellido, j.segundoapellido, e.nombreEquipo) "
			+ "FROM Jugador j LEFT JOIN j.equipo e WHERE j.posicion = :buscarPosicion")
	
	/**
	 * Ocurre que el anterior metodo pero se puede buscar jugadores con y sin
	 * equipos por crear una consulta de LEFT JOIN
	 * 
	 * @param posicion
	 * @return
	 */
	List<JugadorConEquipoDTO> findByPositionDTOv2(@Param("buscarPosicion") String posicion);

	@Query("SELECT j FROM Jugador j WHERE j.posicion = :agregarPosicion")
	/**
	 * Ver jugadores con y sin equipos por la posicion usando identidad
	 * 
	 * @param posicion
	 * @return
	 */
	List<Jugador> findByPositionEntity(@Param("agregarPosicion") String posicion);

	/**
	 * Este meteodo ocurre lo mismo que el anterior y no tiene sentido que exista
	 * 
	 * @param posicion
	 * @return
	 */
	List<Jugador> findByPosicion(String posicion);
/*	
	@Query("SELECT new com.futbol.model.Jugador(j.nombre, j.primerapellido, j.segundoapellido, e.nombreEquipo) "
			+ "FROM Jugador j LEFT JOIN j.equipo e WHERE j.posicion = :buscarPosicion")
			*/
	/**
	 * Esto aun no la he tocado y tengo que modificar
	 * @param posicion
	 * @return
	 */
//	List<Jugador>findByPositionEntityv3(@Param("buscarPorsicion") String posicion);

	// -----------------------------------------------
	Optional<Jugador> findByDocument(String document);
	
	/*
	@Query()
	Optional<JugadorDTO> findByDocumentDTO(@Param("buscarDocument") String document);
	*/
	

	void deleteByDocument(String document);

	@Query("SELECT COUNT(j) FROM Jugador j WHERE j.nombre = :nombre AND j.primerapellido = :primerapellido AND j.segundoapellido = :segundoapellido")
	/**
	 * PARA EVITAR QUE NO HAYA NOMBRE COMPLETO DUPLICADO
	 * 
	 * @param nombre
	 * @param primerapellido
	 * @param segundoapellido
	 * @return
	 */
	Integer contarJugadoresConNombreCompleto(@Param("nombre") String nombre,
			@Param("primerapellido") String primerapellido, @Param("segundoapellido") String segundoapellido);

	// -----------------------------------------------
	// ----------------------------------PUT----------------------------------
	@Transactional
	@Modifying
	@Query("UPDATE Jugador j SET j.edad = :modificarEdad WHERE j.document = :obtenerDocument")
	void actualizarEdad(@Param("obtenerDocument") String document, @Param("modificarEdad") Integer edad);
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("UPDATE Jugador j SET j.idequipo=null WHERE j.document = :obtenerDocument"
	 * ) void desvincularEquipoV1(@Param("obtenerDocument") String document);
	 */
}
