package com.commonbookstore.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private UUID id;

    @Schema(description = "Correo electronico", example = "user@example.com")
    @Email(message = "El correo electronico debe tener un formato valido")
    @NotNull(message = "El Id de email es obligatorio")
    @NotEmpty(message = "El correo electronico no puede estar vacio")
    private String mail;

    @Schema(description = "Descripcion", example = "Descripcion del correo electronico")
    @Size(max = 300, message = "La descripcion no puede exceder 300 caracteres")
    private String description;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private OffsetDateTime createdTime;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private OffsetDateTime updatedTime;

}
