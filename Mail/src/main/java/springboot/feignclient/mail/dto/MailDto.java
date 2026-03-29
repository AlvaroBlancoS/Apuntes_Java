package springboot.feignclient.mail.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDto {
    @Schema(description = "ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;
    @Schema(description = "Correo electrónico", example = "user@example.com")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String mail;
    @Schema(description = "Descripción", example = "Descripción del correo electrónico")
    @Size(max = 300, message = "La descripción no puede exceder 300 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "La descripción solo puede contener letras y espacios")
    private String description;

}
