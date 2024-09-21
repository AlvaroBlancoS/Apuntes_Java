package com.futbol.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {

	private Integer idequipo;
	@NotEmpty(message = "El nombre del equipo es obligatorio")
	private String nombreEquipo;
	@NotEmpty(message = "El nombre del estadio es obligatorio")
	private String estadio;
	@NotEmpty(message = "Es pbligatorio rellenar un pais")
	private String pais;

}
