package com.futbol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.futbol.model.Jugador;
@Repository
@EnableJpaRepositories
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
	Optional<Jugador>findByDocument(String document);
	void deleteByDocument(String document);
	/*
	@Query("SELECT j.nombre, e.nombre FROM Jugadores j INNER JOIN equipos e on e.idequipo=j.idequipo")
	List<String>seeOnlyPlayersWithTeam();//Ahi no se si funcionara... Tengo miedo :(
	*/
	List<Jugador>findByNombre(String nombre);
	
	/*
	 //No esta funcionando... ?
	@Query("SELECT e.nombre, e.primerapellido, e.segundoapellido, COUNT(*) FROM jugadores e GROUP BY e.nombre, e.primerapellido, e.segundoapellido HAVING COUNT(*) > 1")
	Integer nombreCompletoDuplicado();
	*/
	
	@Query("SELECT COUNT(j) FROM Jugador j WHERE j.nombre = :nombre AND j.primerapellido = :primerapellido AND j.segundoapellido = :segundoapellido")
    Integer contarJugadoresConNombreCompleto(@Param("nombre") String nombre, @Param("primerapellido") String primerapellido, @Param("segundoapellido") String segundoapellido);

	//MODIFICAR
	@Modifying @Query("UPDATE Jugador j SET j.edad = :edad WHERE j.document = :document") 
	void actualizarEdad(@Param("document") String document, @Param("edad") Integer edad);
}
