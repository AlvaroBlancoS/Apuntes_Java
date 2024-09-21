package com.futbol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Esto es una una clase sin necesidad de 
 * actualizar dni o nif.
 */
public class JugadorUpdateDTO {
	
	private String nombre;
	private String primerapellido;
	private String segundoapellido;
	private int edad;
	private String posicion;

}
