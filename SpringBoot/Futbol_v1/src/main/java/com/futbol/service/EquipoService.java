package com.futbol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbol.model.Equipo;
import com.futbol.repository.EquipoRepository;
import com.futbol.util.EquipoNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class EquipoService {

	private final EquipoRepository equipoRepository;

	@Autowired
	public EquipoService(EquipoRepository equipoRepository) {
		this.equipoRepository = equipoRepository;
	}

	public Equipo guardarEquipo(Equipo equipo) {
		return equipoRepository.save(equipo);
	}

	public Optional<Equipo> buscarPorId(int id) {
		return equipoRepository.findById(id);
	}

	public List<Equipo> buscarTodos() {
		return equipoRepository.findAll();
	}

	public List<Equipo> buscarEquiposPorPais(String pais) {
		return equipoRepository.findByPais(pais);
	}

	/**
	 * Es mas corto y sencillo
	 * Lo descubri por experiencia... maravilloso
	 * @param pais
	 * @return
	 */
	public List<String> buscarEstadiosPorPais(String pais) {
		List<Equipo> equipos = equipoRepository.findByPais(pais);
		return equipos.stream().map(Equipo::getEstadio).collect(Collectors.toList());
	}

	/**
	 * Es largo y tipico de programador junior
	 * 
	 * @param pais
	 * @return
	 */
	public List<String> buscarEstadiosPorPais_v2(String pais) {
		List<Equipo> equipos = equipoRepository.findByPais(pais);
		List<String> almacenar = new ArrayList<>();
		for (Equipo e : equipos) {
			almacenar.add(e.getEstadio());
		}
		return almacenar;
	}

	public List<String> listaDePaisesExistentes() {
		List<Equipo> equipos = new ArrayList<>();
		return equipos.stream().map(Equipo::getPais).collect(Collectors.toList());
	}

	public boolean paisExistente(String pais) {
		List<Equipo> equipos = equipoRepository.findByPais(pais);
		for (Equipo e : equipos) {
			if (e.getPais().equalsIgnoreCase(pais)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean buscarEstadioExistente_v2(String estadio) {
		List<Equipo> equipos = equipoRepository.findByEstadio(estadio);
		for (Equipo e : equipos) {
			if (e.getEstadio().equalsIgnoreCase(estadio)) {
				return true;
			}
		}
		return false;
	}
	
    public boolean buscarEstadioExistente(String estadio) {
        return equipoRepository.existsByEstadio(estadio);
    } 

	public void eliminarEquipo(int id) {
		equipoRepository.deleteById(id);
	}
	
	public Equipo actualizarEquipo(int id, Equipo equipoActualizado) {
		Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
		String estadio = equipoActualizado.getEstadio();
		boolean verificarEstadio = equipoRepository.existsByEstadio(estadio);
		if (optionalEquipo.isPresent()) {
			if (verificarEstadio) {
				throw new EquipoNotFoundException("El estadio " + estadio + " existe y no puede tener dos o más equipos");
			}
			Equipo equipoExistente = optionalEquipo.get();
			equipoExistente.setNombre(equipoActualizado.getNombre());
			equipoExistente.setEstadio(equipoActualizado.getEstadio());
			equipoExistente.setPais(equipoActualizado.getPais());
			return equipoRepository.save(equipoExistente);
		} else {
			throw new EquipoNotFoundException(id + " id equipo no existe");
		}
	}

	public Equipo actualizarEstadio(String equipo, Equipo equipoActualizado) {
		Optional<Equipo> optionalEquipo = equipoRepository.findByNombre(equipo);
		String nuevoEstadio = equipoActualizado.getEstadio();
		boolean verificarEstadio = equipoRepository.existsByEstadio(nuevoEstadio);
		if (optionalEquipo.isPresent()) {
			if (verificarEstadio) {
				throw new EquipoNotFoundException(nuevoEstadio + " no puede tener dos o más equipos");
			}
			Equipo equipoExistente = optionalEquipo.get();
			equipoExistente.setEstadio(nuevoEstadio);
			return equipoRepository.save(equipoExistente);
		} else {
			throw new EquipoNotFoundException(equipo + " no existe");
		}
	}

	public Equipo cambiarPais(String equipoPais, Equipo equipoActualizado) {
		Optional<Equipo> optionalEquipo = equipoRepository.findByNombre(equipoPais);
		String nuevoPais = equipoActualizado.getPais();
		if (optionalEquipo.isPresent()) {
			Equipo equipoExistente = optionalEquipo.get();
			equipoExistente.setPais(nuevoPais);
			return equipoRepository.save(equipoExistente);
		} else {
			throw new EquipoNotFoundException(equipoPais + " no existe");
		}

	}

	public Equipo modificarNombre(String nombreOriginal, Equipo equipoActualizado) {
		Optional<Equipo> optionalEquipo = equipoRepository.findByNombre(nombreOriginal);
		String nombreModificado = equipoActualizado.getNombre();
		if (optionalEquipo.isPresent()) {
			Equipo equipoExistente = optionalEquipo.get();
			equipoExistente.setNombre(nombreModificado);
			return equipoRepository.save(equipoExistente);
		} else {
			throw new EquipoNotFoundException(nombreOriginal + " no existe");
		}
	}

	public Optional<Equipo> buscarPorNombre(String nombre) {
		return equipoRepository.findByNombre(nombre);
	}
	
	public List<String> listarEstadios() {
		return equipoRepository.seeOnlyEstadios();
	}

	public int contarEstadios() {
		return equipoRepository.countEstadios();
	}

}
