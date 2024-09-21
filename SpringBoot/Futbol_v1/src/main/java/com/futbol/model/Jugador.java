package com.futbol.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jugadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name="Jugador", description = "Jugador Class")//Esto suele utilizar OpenAPI y Swagger
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    @Schema(name= "id", 
	description = "Identificador único para el jugador", 
    example = "42", 
    required = true)
    private Integer idjugador;
    
    @ManyToOne
    @JoinColumn(name = "idequipo", referencedColumnName = "idequipo")
    @Schema(name= "id", 
	description = "Es una clave foranea para indentificar que jugador forma para de su equipo", 
    example = "42", 
    required = true)
    private Equipo equipo;
    
	@NotEmpty(message = "Es obligatorio de agregar el nombre del jugador")
    @Column(name = "nombre")
    private String nombre;
    
	@NotEmpty(message = "Es obligatorio de agregar el primer apellido del jugador")
    @Column(name = "primerapellido")
    private String primerapellido;

    @Column(name = "segundoapellido")
    private String segundoapellido;
    
    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    @Max(value = 150, message = "La edad debe ser menor o igual a 150")
    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "posicion")
    private String posicion;   
    
	@NotEmpty(message = "Es obligatorio de agregrar NIF o NIE")
    @Column(name="document")
    private String document;
}
