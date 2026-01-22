package com.SpringBoot.miniFlyway.interfaces;

import com.SpringBoot.miniFlyway.Annotation.ValidDto;
import com.SpringBoot.miniFlyway.Annotation.ValidEntity;

import jakarta.validation.constraints.NotNull;

public interface Mapper<@ValidEntity Entity, @ValidDto DTO> {

    public Entity convertToEntity(@NotNull DTO dto);

    public DTO convertToDto(@NotNull Entity entity);

    public Entity update(DTO dto, Entity existingEntity);

}
