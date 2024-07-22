package com.futbol.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jugadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Jugador", description = "Jugador Class")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    @Schema(name= "id", 
	description = "Identificador único para el jugador", 
    example = "42", 
    required = true)
    private Long idjugador;
    
    @ManyToOne
    @JoinColumn(name = "idequipo", referencedColumnName = "idequipo")
    @Schema(name= "id", 
	description = "Es una clave foranea para indentificar que jugador forma para de su equipo", 
    example = "42", 
    required = true)
    private Equipo idequipo;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "primerapellido")
    private String primerapellido;

    @Column(name = "segundoapellido")
    private String segundoapellido;
    
    @Column(name = "edad")
    private int edad;
    
    @Column(name = "posicion")
    private String posicion;    
}
