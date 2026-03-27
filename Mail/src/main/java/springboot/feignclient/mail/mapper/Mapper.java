package springboot.feignclient.mail.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.mail.dto.MailDto;
import springboot.feignclient.mail.entity.Mail;


@Component
@RequiredArgsConstructor
public class Mapper {

    public Mail convertToEntity(MailDto dto) {

       return Mail.builder()
                .mail(dto.getMail())
                .description(dto.getDescription())
                .build();
    }

    public MailDto convertToDto(Mail entity) {

        return MailDto.builder()
                .mail(entity.getMail())
                .description(entity.getDescription())
                .build();
    }

    public Mail updateMail(MailDto dto, Mail entity){
        entity.setMail(dto.getMail());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
