package springboot.mapper;

import java.util.UUID;

public interface Mapper <ENTITY, DTO> {

    public ENTITY convertToEntity(DTO dto);

    public DTO convertToDto(ENTITY entity);

    public ENTITY updateEntity(DTO dto, ENTITY entityExisting);

    public ENTITY getEntityById(UUID id);

    public ENTITY getEntityByName(String name);

}
