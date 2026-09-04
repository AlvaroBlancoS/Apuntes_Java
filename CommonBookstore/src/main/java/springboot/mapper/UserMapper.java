package springboot.mapper;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springboot.dto.UserDto;
import springboot.entity.User;


@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    
    private final PasswordEncoder passwordEncoder;
    private final MailMapper mailMapper;
    

    @Override
    public User convertToEntity(UserDto dto) {
                return User.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .mailId(mailMapper.getEntityById(dto.getMailId()).getId())
                .build();
    }

    @Override
    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mailDto(mailMapper.convertToDto(mailMapper.getEntityById(entity.getMailId())))
                .build();
    }

    @Override
    public User updateEntity(UserDto dto, User entityExisting) {
            entityExisting.setName(dto.getName() != null ? dto.getName() : entityExisting.getName());
        entityExisting.setPassword(
                dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : entityExisting.getPassword());
        entityExisting.setMailId(dto.getMailId() != null ? mailMapper.getEntityById(dto.getMailId()).getId() : entityExisting.getMailId());
        return entityExisting;
    }
}
