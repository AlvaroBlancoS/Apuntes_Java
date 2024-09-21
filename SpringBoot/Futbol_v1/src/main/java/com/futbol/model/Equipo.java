package com.futbol.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Equipo", description = "Equipo Class")//Esto suele utilizar OpenAPI y Swagger
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idequipo")
	@Schema(name = "id", description = "Identificador único para el equipo", example = "1", required = true)
	private Integer idequipo;

	@NotEmpty(message = "El nombre del equipo es obligatorio")
	@Column(name = "nombre")
	private String nombreEquipo;

	@Column(name = "estadio")
	@NotEmpty(message = "El nombre del estadio es obligatorio")
	private String estadio;

	@NotEmpty(message = "Es pbligatorio rellenar un pais")
	@Column(name = "pais")
	private String pais;

}
