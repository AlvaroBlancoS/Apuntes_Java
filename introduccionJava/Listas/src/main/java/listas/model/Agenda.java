package listas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@ToString
/**
 * 
 * @author Alvaro Blanco Sangines He utilizado lombok para ahorrar el tiempo de
 *         escribir el constructor y los metodos de getters y setter
 */
public class Agenda {
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
