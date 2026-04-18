package springboot.feignclient.user.dto;

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
public class ChangePasswordDto {

    @Schema(description = "Nombre", example = "John Doe")
    @NotNull(message = "El nombre de usuario es obligatorio")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String userName;

    @Schema(description = "Contraseña actual", example = "currentpassword123")
    @NotNull(message = "La contraseña actual es obligatoria")
    @NotBlank(message = "La contraseña actual no puede estar vacía")
    private String currentPassword;

    @Schema(description = "Nueva contraseña", example = "newpassword123")
    @NotNull(message = "La nueva contraseña es obligatoria")
    @NotBlank(message = "La nueva contraseña no puede estar vacía")
    private String newPassword;
}
