package com.futbol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futbol.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Optional<Equipo> findByNombre(String nombre);//Buscar por nombre del equipo
    List<Equipo> findByPais(String pais);// Lista de paises existentes
    List<Equipo> findByEstadio(String estadio);//Lista de estadios existentes
    //Esto parece que no funciona del todo
    boolean existsByEstadio(String estadio);
    @Query("SELECT e.estadio FROM Equipo e")
    List<String>seeOnlyEstadios();
    @Query("SELECT COUNT(DISTINCT e.estadio) FROM Equipo e")
    int countEstadios();
    @Query("SELECT COUNT(e.nombre) FROM Equipo e GROUP BY e.nombre HAVING COUNT(e.nombre) > 1")
    Integer nombreDuplicado();//Rara vez, averiguando si hay nombres duplicados en la base de datos
}
