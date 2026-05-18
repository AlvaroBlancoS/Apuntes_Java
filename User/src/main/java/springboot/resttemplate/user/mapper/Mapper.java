package springboot.resttemplate.user.mapper;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import springboot.resttemplate.user.client.MailRestTemplateClient;
import springboot.resttemplate.user.dto.UserDto;
import springboot.resttemplate.user.entity.User;

import com.commonbookstore.dto.MailDto;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final MailRestTemplateClient mailRestTemplateClient;
    private final PasswordEncoder passwordEncoder;

    public User convertToEntity(UserDto dto) {
        MailDto mailDto = getMailById(dto.getMailId());
        return User.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .mailId(mailDto.getId())
                .build();
    }

    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mailDto(getMailById(entity.getMailId()))
                .createdTime(entity.getCreatedTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }

    public User updateUser(UserDto dto, User entity) {
        entity.setName(dto.getName() != null ? dto.getName() : entity.getName());
        entity.setPassword(
                dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : entity.getPassword());
        entity.setMailId(dto.getMailId() != null ? getMailById(dto.getMailId()).getId() : entity.getMailId());
        return entity;
    }

    public MailDto getMailById(UUID idMail) {
        MailDto mailDto = mailRestTemplateClient.getMailById(idMail);
        if (mailDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El mail con id " + idMail + " no existe");
        }
        return mailDto;
    }

    public MailDto getMailByName(String name) {
        MailDto mailDto = mailRestTemplateClient.getMailByName(name);
        if (mailDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El mail con nombre " + name + " no existe");
        }
        return mailDto;
    }
}
