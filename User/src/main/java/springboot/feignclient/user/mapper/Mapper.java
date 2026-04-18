package springboot.feignclient.user.mapper;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import springboot.feignclient.user.client.MailFeignClient;
import springboot.feignclient.user.dto.MailDto;
import springboot.feignclient.user.dto.UserDto;
import springboot.feignclient.user.entity.User;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final MailFeignClient mailFeignClient;
    private final PasswordEncoder passwordEncoder;

    public User convertToEntity(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .mailId(mailFeignClient.getMailById(dto.getMailId()).getId())
                .build();
    }

    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mailDto(getMailById(entity.getMailId()))
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
        try {
            return mailFeignClient.getMailById(idMail);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El mail con id " + idMail + " no existe");
        }
    }

    public MailDto getMailByName(String name) {
        try {
            return mailFeignClient.getMailByName(name);

        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El mail con nombre " + name + " no existe");
        }
    }
}
