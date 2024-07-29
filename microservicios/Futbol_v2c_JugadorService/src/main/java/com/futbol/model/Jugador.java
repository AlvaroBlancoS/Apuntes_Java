package com.futbol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jugadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idjugador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idequipo", referencedColumnName = "idequipo")
	private Equipo equipo;

	@NotEmpty(message = "El nombre del equipo es obligatorio")
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty(message = "El primer apellido es obligatorio")
	@Column(name = "primerapellido")
	private String primerapellido;

	@Column(name = "segundoapellido")
	private String segundoapellido;
	
	@NotEmpty(message = "Es obligatorio de rellenar la edad")
	@Column(name= "edad")
	private int edad;
	
	@Column(name="posicion")
	private String posicion;

}
