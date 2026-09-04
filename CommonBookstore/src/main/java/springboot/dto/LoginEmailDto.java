package springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginEmailDto {

    
    @Schema(description = "Nombre", example = "John Doe")
    @NotNull(message = "El Correo electronico es obligatorio")
    @NotBlank(message = "El Correo electronico  no puede estar vacío")
    private String mail;
    @Schema(description = "Contraseña", example = "password123")
    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
