package com.SpringBoot.miniFlyway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDto {

    @Schema(description = "", accessMode = AccessMode.READ_ONLY)
    private Long id;
    private String title;
    private String author;
    private int cant;
}
