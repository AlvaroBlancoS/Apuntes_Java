package com.futbol.DTO;

import lombok.Data;

@Data
public class JugadorDTO {
	private String nombre;
	private String primerapellido;
	private String segundoapellido;
	private int edad;
	private String posicion;
	private String nombreEquipo;

	public JugadorDTO(String nombre, String primerapellido, String segundoapellido, int edad, String posicion,
			String nombreEquipo) {
		this.nombre = nombre;
		this.primerapellido = primerapellido;
		this.segundoapellido = segundoapellido;
		this.edad = edad;
		this.posicion = posicion;
		this.nombreEquipo = nombreEquipo;
	}

	public JugadorDTO(String nombre, String primerapellido, String segundoapellido, int edad,
			String posicion) {
		this.nombre = nombre;
		this.primerapellido = primerapellido;
		this.segundoapellido = segundoapellido;
		this.edad = edad;
		this.posicion = posicion;
	}
	
	public JugadorDTO(String nombre, String posicion) {
		this.nombre = nombre;
		this.posicion = posicion;
	}

}
