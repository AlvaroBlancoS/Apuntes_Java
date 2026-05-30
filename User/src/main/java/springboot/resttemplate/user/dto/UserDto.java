package springboot.resttemplate.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.commonbookstore.dto.MailDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nombre de usuario", example = "franco")
    @NotNull(message = "El nombre de usuario es obligatorio")
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    @Pattern(regexp = "^[a-z][a-z0-9._-]*$", message = "El nombre de usuario solo puede contener minúsculas, números, puntos, guiones y guiones bajos, sin espacios")
    private String name;

    @Schema(description = "Contraseña", example = "password123", accessMode = Schema.AccessMode.WRITE_ONLY)
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial.")
    @NotNull(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @Schema(description = "ID del Mail asociado", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotNull(message = "El Id de email es obligatorio")
    private UUID mailId;

    @Schema(description = "Nombre del Mail asociado", example = "john.doe@example.com", accessMode = Schema.AccessMode.READ_ONLY)
    private MailDto mailDto;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdTime;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updateTime;

}
