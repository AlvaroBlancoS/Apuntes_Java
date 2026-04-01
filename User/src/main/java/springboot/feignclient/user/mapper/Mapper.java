package springboot.feignclient.user.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.user.dto.UserDto;
import springboot.feignclient.user.entity.User;

@Component
@RequiredArgsConstructor
public class Mapper {

    public User convertToEntity(UserDto dto) {

        return User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .mailId(dto.getMailId())
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
        entity.setMailId(dto.getMailId());
        return entity;
    }

}
