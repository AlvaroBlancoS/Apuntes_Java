package springboot.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El nombre de usuario es obligatorio")
    private String name;

    @Schema(description = "Contraseña", example = "password123", accessMode = Schema.AccessMode.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial.")
    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    @Schema(description = "ID del Mail asociado", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotNull(message = "El Id de email es obligatorio")
    private UUID mailId;

    @Schema(description = "Nombre del Mail asociado", example = "john.doe@example.com", accessMode = Schema.AccessMode.READ_ONLY)
    private MailDto mailDto;
}
