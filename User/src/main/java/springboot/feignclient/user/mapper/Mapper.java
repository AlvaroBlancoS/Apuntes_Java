package springboot.feignclient.user.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.user.client.MailFeignClient;
import springboot.feignclient.user.dto.UserDto;
import springboot.feignclient.user.entity.User;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final MailFeignClient mailFeignClient;

    public User convertToEntity(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .mailId(isMailIdValid(dto.getMailId())? dto.getMailId() : null)
                .build();
    }

    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .password(entity.getPassword())
                .mailId(entity.getMailId())
                .build();
    }

    public User updateUser(UserDto dto, User entity) {
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setMailId(isMailIdValid(dto.getMailId()) ? dto.getMailId() : null);
        return entity;
    }

    private boolean isMailIdValid(UUID idMail) {
        return mailFeignClient.getMailById(idMail) != null;
    }

}
