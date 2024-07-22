package com.futbol.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.futbol.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Optional<Equipo> findByNombre(String nombre);
    List<Equipo> findByPais(String pais);
    List<Equipo> findByEstadio(String estadio);
    //Esto parece que no funciona del todo
    boolean existsByEstadio(String estadio);
    @Query("SELECT e.estadio FROM Equipo e")
    List<String>seeOnlyEstadios();
    @Query("SELECT COUNT(DISTINCT e.estadio) FROM Equipo e")
    int countEstadios();
    
    
}
