package com.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbol.model.Jugador;
@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
	 // Aquí puedes agregar métodos específicos de consulta si los necesitas
}
