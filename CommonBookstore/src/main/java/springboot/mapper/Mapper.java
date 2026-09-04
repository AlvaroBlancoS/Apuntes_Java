package springboot.mapper;


public interface Mapper <ENTITY, DTO> {

    public ENTITY convertToEntity(DTO dto);

    public DTO convertToDto(ENTITY entity);

    public ENTITY updateEntity(DTO dto, ENTITY entityExisting);

}
