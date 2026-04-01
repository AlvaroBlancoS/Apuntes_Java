package springboot.feignclient.user.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nombre", example = "John Doe")
    private String name;

    @Schema(description = "Contraseña", example = "password123")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial.")
    private String password;

    @Schema(description = "ID del Mail asociado", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID mailId;

}
