package springboot.token.mail.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import springboot.dto.MailDto;
import springboot.entity.Mail;
import springboot.mapper.MailMapper;
import springboot.repository.EmailRepository;
import springboot.util.exception.CustomException;
import springboot.util.exception.MessageException;

@Service
@RequiredArgsConstructor
public class MailService {

    private final EmailRepository emailRepository;
    private final MailMapper mapperEmail;

    public List<MailDto> getAllMails(Sort sort) {
        List<Mail> mails = emailRepository.findAll(sort);
        return mails.stream()
                .map(mapperEmail::convertToDto)
                .collect(Collectors.toList());
    }

    public MailDto getMailByName(String name) {
        Mail mail = emailRepository.findByMail(name)
                .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
        return mapperEmail.convertToDto(mail);
    }

    public MailDto getMailById( UUID id) {
        Mail mail = emailRepository.findById(id)
                .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
        return mapperEmail.convertToDto(mail);
    }

    public MailDto createMail(MailDto dto) {
        boolean mailExists = emailRepository.findByMail(dto.getMail()).isPresent();
        if (mailExists) {
            throw new CustomException(MessageException.MAIL_EXISTS);
        }
        Mail mail = mapperEmail.convertToEntity(dto);
        Mail savedMail = emailRepository.save(mail);
        return mapperEmail.convertToDto(savedMail);
    }

    public MailDto updateMail(UUID id, MailDto dto) {
        Mail existingMail = emailRepository.findById(id)
                .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
        Mail updatedMail = mapperEmail.updateEntity(dto, existingMail);
        Mail savedMail = emailRepository.save(updatedMail);
        return mapperEmail.convertToDto(savedMail);
    }

    public void deleteMailById(UUID id) {
        if (mailExists(null, id, 2)) {
            emailRepository.deleteById(id);
        } else {
            throw new CustomException(MessageException.MAIL_NOT_FOUND);
        }
    }

    public void deleteMailByName(String name) {
        if (mailExists(name, null, 1)) {
            Mail mail = emailRepository.findByMail(name).get();
            emailRepository.delete(mail);
        } else {
            throw new CustomException(MessageException.MAIL_NOT_FOUND);
        }
    }

    private boolean mailExists(String mail, UUID id, int option) {
        if (option == 1) {
            return emailRepository.findByMail(mail).isPresent();
        } else if (option == 2) {
            return emailRepository.findById(id).isPresent();
        }
        return false;
    }
}
