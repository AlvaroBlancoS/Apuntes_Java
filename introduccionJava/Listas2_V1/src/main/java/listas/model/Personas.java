package listas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
@AllArgsConstructor // Genera un constructor con todos los argumento
@ToString
public class Personas {
	@Getter
	@Setter
	private String nombre;
	@Getter
	@Setter
	private String apellidos;
	@Getter
	@Setter
	private String email;
}
