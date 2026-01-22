package com.SpringBoot.miniFlyway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    @Schema(description = "", accessMode = AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "enter name", accessMode = AccessMode.READ_WRITE)
    private String name;

    @Schema(description = "enter email", accessMode = AccessMode.READ_WRITE)
    private String email;

    @Schema(description = "enter age", accessMode = AccessMode.READ_WRITE)
    private Integer age;
}
