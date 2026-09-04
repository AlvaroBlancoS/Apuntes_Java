package springboot.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springboot.dto.MailDto;
import springboot.entity.Mail;
import springboot.repository.EmailRepository;
import springboot.util.exception.CustomException;
import springboot.util.exception.MessageException;

@Component
@RequiredArgsConstructor
public class MailMapper implements Mapper<Mail, MailDto> {

    private final EmailRepository emailRepository;

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

    public Mail getEntityById(UUID id) {
        return emailRepository.findById(id).orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
    }

    public Mail getEntityByName(String name) {
        return emailRepository.findByMail(name)
                .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
    }

}
