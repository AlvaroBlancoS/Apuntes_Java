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
import com.futbol.service.ReciclajeIDService;
import com.futbol.util.EquipoBadRequest;
import com.futbol.util.EquipoNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipo", description = "Equipo API")
public class EquipoController {
	@Autowired
	private final EquipoService equipoService;
	private final ReciclajeIDService reciclar;

	public EquipoController(EquipoService equipoService, ReciclajeIDService reciclar) {
		this.equipoService = equipoService;
		this.reciclar = reciclar;
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo equipo", description = "Registra un nuevo equipo en la base de datos", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Equipo registrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content) })
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

		// No funciona de momento
		Integer reciclarID = reciclar.getReciclarID();
		if (reciclarID != null) {
			equipo.setIdequipo(reciclarID);
		}

		Equipo nuevoEquipo = equipoService.guardarEquipo(equipo);
		return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);

	}

	// ----------------------------GET---------------------------
	@GetMapping("/{id}")
	@Operation(summary = "Obtener equipo por ID", description = "Devuelve un equipo dado su ID", tags = { "equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipo encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable("id") int id) {
		Optional<Equipo> equipo = equipoService.buscarPorId(id);
		if (equipo.isPresent()) {
			return new ResponseEntity<>(equipo.get(), HttpStatus.OK);
		} else {
			// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new EquipoNotFoundException("Equipo no encontrado con id: " + id);
		}
		// equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("verSoloID/{nombreEquipo}")
	public ResponseEntity<Integer> obtenerIDequipoPorNombre(@PathVariable("nombreEquipo") String nombreEquipo) {
		Optional<Integer> buscarNombreEquipo = equipoService.buscarIDPorNombre(nombreEquipo);
		if (buscarNombreEquipo.isPresent()) {
			return new ResponseEntity<>(buscarNombreEquipo.get(), HttpStatus.OK);
		} else {
			throw new EquipoNotFoundException("No se encuentra nombre de equipo: " + nombreEquipo);
		}
	}

	public ResponseEntity<Integer> obtenerIDequipoPorNombrev2(@PathVariable("nombreEquipo") String nombreEquipo) {
		Optional<Equipo> buscarNombreEquipo = equipoService.buscarPorNombre(nombreEquipo);
		if (buscarNombreEquipo.isPresent()) {
			return new ResponseEntity<>(buscarNombreEquipo.get().getIdequipo(), HttpStatus.OK);
		} else {
			throw new EquipoNotFoundException("No se encuentra nombre de equipo: " + nombreEquipo);
		}
	}

	@GetMapping("/nombreEquipo/{nombre}")
	@Operation(summary = "Obtener equipo por nombre", description = "Devuelve un equipo dado su nombre", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipo encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> obtenerEquipoPorNombre(@PathVariable("nombre") String nombre) {
		Optional<Equipo> equipo = equipoService.buscarPorNombre(nombre);
		if (!(equipo.isPresent())) {
			throw new EquipoNotFoundException(nombre + " no aparece en la lista");
		}
		return equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	@Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de todos los equipos", tags = {
			"equipo" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de equipos", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }) })
	public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
		List<Equipo> equipos = equipoService.buscarTodos();
		return ResponseEntity.ok(equipos);
	}

	@GetMapping("/verEquipos/{pais}")
	@Operation(summary = "Obtener equipos por país", description = "Devuelve una lista de equipos por país", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de equipos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "No se encontraron equipos para el país especificado", content = @Content) })
	public ResponseEntity<List<Equipo>> obtenerEquiposPorPais(@PathVariable("pais") String pais) {
		List<Equipo> equipos = equipoService.buscarEquiposPorPais(pais);
		boolean siExiste = equipoService.paisExistente(pais);
		if (!siExiste) {
			throw new EquipoNotFoundException(pais + " no existe o no aparece en la lista");
		}
		return ResponseEntity.ok(equipos);
	}

	@GetMapping("/estadios/{pais}")
	@Operation(summary = "Obtener estadios por país", description = "Devuelve una lista de estadios por país", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "lista de estadios por pais encontrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "No existe o no aparece en la lista", content = @Content) })
	public ResponseEntity<List<String>> obtenerEstadiosPorPais(@PathVariable("pais") String pais) {
		List<String> estadios = equipoService.buscarEstadiosPorPais_v2(pais);
		boolean siExiste = equipoService.paisExistente(pais);
		if (!siExiste) {
			throw new EquipoNotFoundException(pais + " no existe o no aparece en la lista");
		}
		return ResponseEntity.ok(estadios);
	}
	// ----------------------------------PUT-------------------
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar equipo", description = "Actualiza un equipo dado su ID", tags = { "equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipo actualizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> actualizarEquipo(@Valid @PathVariable("id") int id,
			@RequestBody Equipo equipoActualizado) {
		Equipo equipo = equipoService.actualizarEquipo(id, equipoActualizado);
		return ResponseEntity.ok(equipo);
	}

	@PutMapping("/modificarEstadio/{nombreEquipo}")
	@Operation(summary = "Modificar estadio del equipo", description = "Modifica el estadio de un equipo dado su nombre", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estadio modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> modificarEstadio(@Valid @PathVariable("nombreEquipo") String nombreEquipo,
			@RequestBody Equipo nuevoEstadio) {
		Equipo editarEstadio = equipoService.actualizarEstadio(nombreEquipo, nuevoEstadio);
		return ResponseEntity.ok(editarEstadio);
	}

	@PutMapping("/cambiarPais/{nombreEquipo}")
	@Operation(summary = "Cambiar país del equipo", description = "Cambia el país de un equipo dado su nombre", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "País cambiado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> cambiarPais(@Valid @PathVariable("nombreEquipo") String nombreEquipo,
			@RequestBody Equipo paisCambiado) {
		Equipo cambiarPais = equipoService.cambiarPais(nombreEquipo, paisCambiado);
		return ResponseEntity.ok(cambiarPais);
	}

	@PutMapping("/modificarNombre/{nombreOriginal}")
	@Operation(summary = "Modificar nombre del equipo", description = "Modifica el nombre de un equipo dado su nombre original", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Nombre modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Equipo> modificarNombre(@Valid @PathVariable("nombreOriginal") String nombreOriginal,
			@RequestBody Equipo nombreCambiado) {
		Equipo editarNombre = equipoService.modificarNombreEquipo_nombre(nombreOriginal, nombreCambiado);
		return ResponseEntity.ok(editarNombre);
	}

	@Operation(summary = "Modificar nombre del equipo por ID", description = "Modifica el nombre de un equipo dado su ID Equipo", tags = {
			"equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Nombre modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "ID Equipo no encontrado", content = @Content) })
	@PutMapping("/modificarNombreID/{id}")
	public ResponseEntity<Equipo> modificarNombreEquipo_id(@Valid @PathVariable("id") int id,
			@RequestBody Equipo nombreCambiado) {
		Equipo editarNombre = equipoService.modificarNombreEquipo_id(id, nombreCambiado);
		return ResponseEntity.ok(editarNombre);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un equipo", description = "Se busca por ID para eliminar equpo", tags = { "equipo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipo encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Equipo.class)) }),
			@ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content) })
	public ResponseEntity<Void> eliminarEquipo(@PathVariable("id") int id) {
		Optional<Equipo> equipo = equipoService.buscarPorId(id);
		if (!(equipo.isPresent())) {
			throw new EquipoNotFoundException(id + " de equipo no existe ");
		}
		equipoService.eliminarEquipo(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/estadios")
	@Operation(summary = "Listar estadios", description = "Devuelve una lista de todos los estadios", tags = {
			"equipo" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de estadios", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }) })
	public ResponseEntity<List<String>> listarEstadios() {
		List<String> estadios = equipoService.listarEstadios();
		return ResponseEntity.ok(estadios);
	}

	@GetMapping("/totalEstadios")
	@Operation(summary = "Contar estadios", description = "Devuelve el número total de estadios", tags = { "equipo" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Total de estadios", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }) })
	public ResponseEntity<Integer> contarEstadios() {
		int totalEstadios = equipoService.contarEstadios();
		return ResponseEntity.ok(totalEstadios);
	}

	/**
	 * Método de prueba para lanzar una excepción y probar el manejo de excepciones.
	 * 
	 * @return
	 */
	@GetMapping("/test")
	public ResponseEntity<String> lanzarExcepcion() {
		throw new RuntimeException("Probando si funciona manejador de excepciones");
	}
}
