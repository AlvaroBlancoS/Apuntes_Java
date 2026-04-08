package springboot.feignclient.mail.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.mail.dto.MailDto;
import springboot.feignclient.mail.entity.Mail;
import springboot.feignclient.mail.mapper.Mapper;
import springboot.feignclient.mail.repository.EmailRepository;

@Service
@RequiredArgsConstructor
public class MailService {

    private final EmailRepository emailRepository;
    private final Mapper mapperEmail;

    public List<MailDto> getAllMails(Sort sort) {
        List<Mail> mails = emailRepository.findAll(sort);
        return mails.stream()
                .map(mapperEmail::convertToDto)
                .collect(Collectors.toList());
    }

    public MailDto getMailByName(String name) {
        Mail mail = emailRepository.findByMail(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo electrónico no encontrado"));
        return mapperEmail.convertToDto(mail);
    }

    public MailDto getMailById( UUID id) {
        Mail mail = emailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Correo electrónico no encontrado"));
        return mapperEmail.convertToDto(mail);
    }

    public MailDto createMail(MailDto dto) {
        boolean mailExists = emailRepository.findByMail(dto.getMail()).isPresent();
        if (mailExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo electrónico ya existe");
        }
        Mail mail = mapperEmail.convertToEntity(dto);
        Mail savedMail = emailRepository.save(mail);
        return mapperEmail.convertToDto(savedMail);
    }

    public MailDto updateMail(UUID id, MailDto dto) {
        Mail existingMail = emailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo electrónico no encontrado"));
        Mail updatedMail = mapperEmail.updateMail(dto, existingMail);
        Mail savedMail = emailRepository.save(updatedMail);
        return mapperEmail.convertToDto(savedMail);
    }

    public void deleteMailById(UUID id) {
        if (mailExists(null, id, 2)) {
            emailRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo electrónico no encontrado");
        }
    }

    public void deleteMailByName(String name) {
        if (mailExists(name, null, 1)) {
            Mail mail = emailRepository.findByMail(name).get();
            emailRepository.delete(mail);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo electrónico no encontrado");
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
