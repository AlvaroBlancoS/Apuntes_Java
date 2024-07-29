package com.futbol.model;

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
public class Equipo {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idequipo;
	
	@NotEmpty(message = "El nombre del equipo es obligatorio")
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "estadio")
	@NotEmpty(message = "El nombre del estadio es obligatorio")
	private String estadio;

	@NotEmpty(message = "Es pbligatorio rellenar un pais")
	@Column(name = "pais")
	private String pais;
	
}
