package com.futbol.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JugadorConEquipoDTO {

	private Integer edad;
	private String nombre;
	private String primerapellido;
	private String segundoapellido;
	private String posicion;
	@NotEmpty(message = "Es obligatorio rellenar el documento")
	private String document;
	private Integer idequipo;

	@NotEmpty(message = "El nombre del equipo es obligatorio")
	private String nombreEquipo;
	@NotEmpty(message = "El nombre del estadio es obligatorio")
	private String estadio;
	@NotEmpty(message = "Es obligatorio rellenar un pais")
	private String pais;

	public JugadorConEquipoDTO(String nombre, String primerapellido, String segundoapellido, String nombreEquipo) {
		this.nombre = nombre;
		this.primerapellido = primerapellido;
		this.segundoapellido = segundoapellido;
		this.nombreEquipo = nombreEquipo;
	}

}
