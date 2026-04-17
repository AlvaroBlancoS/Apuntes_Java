package springboot.feignclient.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
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
public class LoginUserDto {

    @Schema(description = "Nombre", example = "John Doe")
    @NotNull(message = "El nombre de usuario es obligatorio")
    private String userName;
    @Schema(description = "Contraseña", example = "password123")
    @NotNull(message = "La contraseña es obligatoria")
    private String password;

}
