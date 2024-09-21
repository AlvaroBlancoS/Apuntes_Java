package com.futbol.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.futbol.dto.JugadorConEquipoDTO;
import com.futbol.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Optional<Equipo> findByNombreEquipo(String nombreEquipo);
    List<Equipo> findByPais(String pais);
    List<Equipo> findByEstadio(String estadio);
    //Esto parece que no funciona del todo
    boolean existsByEstadio(String estadio);
    @Query("SELECT e.estadio FROM Equipo e")
    List<String>seeOnlyEstadios();
    @Query("SELECT COUNT(DISTINCT e.estadio) FROM Equipo e")
    int countEstadios();
    @Query("SELECT COUNT(e.nombreEquipo) FROM Equipo e GROUP BY e.nombreEquipo HAVING COUNT(e.nombreEquipo) > 1")
    Integer nombreDuplicado();//Rara vez, averiguando si hay nombres duplicados en la base de datos   
    @Query("SELECT e.idequipo FROM Equipo e WHERE e.nombreEquipo = :buscarNombreEquipo")
    Optional<Integer> findIdByNombreEquipo(@Param("buscarNombreEquipo") String nombreEquipo);
    
    //List<JugadorConEquipoDTO>verEquiposConJugadores(int idEquipo);
}
