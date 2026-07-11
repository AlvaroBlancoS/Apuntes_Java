package springboot.mapper;

import java.util.UUID;

import springboot.dto.MailDto;
import springboot.entity.Mail;

public class MailMapper implements Mapper<springboot.entity.Mail, springboot.dto.MailDto> {

    @Override
    public Mail convertToEntity(MailDto dto) {
        return Mail.builder()
                .mail(dto.getMail())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public MailDto convertToDto(Mail entity) {
        return MailDto.builder()
                .id(entity.getId())
                .mail(entity.getMail())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public Mail updateEntity(MailDto dto, Mail entityExisting) {
        entityExisting.setMail(dto.getMail());
        entityExisting.setDescription(dto.getDescription());
        return entityExisting;
    }

    @Override
    public Mail getEntityById(UUID id) {
        return null;
    }

    @Override
    public Mail getEntityByName(String name) {
        return null;
    }

}
