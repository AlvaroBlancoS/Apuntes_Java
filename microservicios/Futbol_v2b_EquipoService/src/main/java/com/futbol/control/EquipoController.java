package com.futbol.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futbol.model.Equipo;
import com.futbol.service.EquipoService;
import com.futbol.util.EquipoBadRequest;
import com.futbol.util.EquipoNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
	@Autowired
	private final EquipoService equipoService;

	@Autowired
	public EquipoController(EquipoService equipoService) {
		this.equipoService = equipoService;
	}

	@PostMapping
	public ResponseEntity<Equipo> crearEquipo(@Valid @RequestBody Equipo equipo) {
		String nombreEquipo = equipo.getNombre();
		Optional<Equipo> equipoExistente = equipoService.buscarPorNombre(nombreEquipo);
		if (equipoExistente.isPresent()) {
			throw new EquipoBadRequest(nombreEquipo + " ya existe ese nombre de equipo");
		}

		String estadio = equipo.getEstadio();
		boolean estadioExistente = equipoService.buscarEstadioExistente(estadio);
		if (estadioExistente) {
			throw new EquipoBadRequest(estadio + " existe y no se puede agregar dos o mas equipos de un estadio");
		}

		Equipo nuevoEquipo = equipoService.guardarEquipo(equipo);
		return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable("id") int id) {
		Optional<Equipo> equipo = equipoService.buscarPorId(id);
		if (equipo.isPresent()) {
			return new ResponseEntity<>(equipo.get(), HttpStatus.OK);
		} else {
			throw new EquipoNotFoundException("Equipo no encontrado con id: " + id);
		}
	}

	@GetMapping("/nombreEquipo/{nombre}")
	public ResponseEntity<Equipo> obtenerEquipoPorNombre(@PathVariable("nombre") String nombre) {
		Optional<Equipo> equipo = equipoService.buscarPorNombre(nombre);
		if (!(equipo.isPresent())) {
			throw new EquipoNotFoundException(nombre + " no aparece en la lista");
		}
		return equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
		List<Equipo> equipos = equipoService.buscarTodos();
		return ResponseEntity.ok(equipos);
	}

	@GetMapping("/verEquipos/{pais}")
	public ResponseEntity<List<Equipo>> obtenerEquiposPorPais(@PathVariable("pais") String pais) {
		List<Equipo> equipos = equipoService.buscarEquiposPorPais(pais);
		boolean siExiste = equipoService.paisExistente(pais);
		if (!siExiste) {
			throw new EquipoNotFoundException(pais + " no existe o no aparece en la lista");
		}
		return ResponseEntity.ok(equipos);
	}

	@GetMapping("/estadios/{pais}")
	public ResponseEntity<List<String>> obtenerEstadiosPorPais(@PathVariable("pais") String pais) {
		List<String> estadios = equipoService.buscarEstadiosPorPais_v2(pais);
		boolean siExiste = equipoService.paisExistente(pais);
		if (!siExiste) {
			throw new EquipoNotFoundException(pais + " no existe o no aparece en la lista");
		}
		return ResponseEntity.ok(estadios);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Equipo> actualizarEquipo(@Valid @PathVariable("id") int id,
			@RequestBody Equipo equipoActualizado) {
		Equipo equipo = equipoService.actualizarEquipo(id, equipoActualizado);
		return ResponseEntity.ok(equipo);
	}

	@PutMapping("/modificarEstadio/{nombreEquipo}")
	public ResponseEntity<Equipo> modificarEstadio(@Valid @PathVariable("nombreEquipo") String nombreEquipo,
			@RequestBody Equipo nuevoEstadio) {
		Equipo editarEstadio = equipoService.actualizarEstadio(nombreEquipo, nuevoEstadio);
		return ResponseEntity.ok(editarEstadio);
	}

	@PutMapping("/cambiarPais/{nombreEquipo}")
	public ResponseEntity<Equipo> cambiarPais(@Valid @PathVariable("nombreEquipo") String nombreEquipo,
			@RequestBody Equipo paisCambiado) {
		Equipo cambiarPais = equipoService.cambiarPais(nombreEquipo, paisCambiado);
		return ResponseEntity.ok(cambiarPais);
	}

	@PutMapping("/modificarNombre/{nombreOriginal}")
	public ResponseEntity<Equipo> modificarNombreEquipo_nombre(@Valid @PathVariable("nombreOriginal") String nombreOriginal,
			@RequestBody Equipo nombreCambiado) {
		Equipo editarNombre = equipoService.modificarNombreEquipo_nombre(nombreOriginal, nombreCambiado);
		return ResponseEntity.ok(editarNombre);
	}
	
	@PutMapping("/modificarNombreID/{id}")
	public ResponseEntity<Equipo> modificarNombreEquipo_id(@Valid @PathVariable("id") int id,
			@RequestBody Equipo nombreCambiado) {
		Equipo editarNombre = equipoService.modificarNombreEquipo_id(id, nombreCambiado);
		return ResponseEntity.ok(editarNombre);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEquipo(@PathVariable("id") int id) {
		Optional<Equipo> equipo = equipoService.buscarPorId(id);
		if (!(equipo.isPresent())) {
			throw new EquipoNotFoundException(id + " de equipo no existe ");
		}
		equipoService.eliminarEquipo(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/estadios")
	public ResponseEntity<List<String>> listarEstadios() {
		List<String> estadios = equipoService.listarEstadios();
		return ResponseEntity.ok(estadios);
	}

	@GetMapping("/totalEstadios")
	public ResponseEntity<Integer> contarEstadios() {
		int totalEstadios = equipoService.contarEstadios();
		return ResponseEntity.ok(totalEstadios);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
