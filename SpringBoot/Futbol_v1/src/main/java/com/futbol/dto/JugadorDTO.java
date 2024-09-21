package com.futbol.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JugadorDTO {
	@NotNull(message = "La edad no puede ser nula")
	@Min(value = 18, message = "La edad debe ser mayor o igual a 18")
	@Max(value = 150, message = "La edad debe ser menor o igual a 150")
	private Integer edad;
	private String nombre;
	private String primerapellido;
	private String segundoapellido;
	private String posicion;
	private String document;

	public JugadorDTO(String nombre, String primerApellido, String SegundoApellido, String posicion) {
		this.nombre = nombre;
		this.posicion = posicion;
	}

}
