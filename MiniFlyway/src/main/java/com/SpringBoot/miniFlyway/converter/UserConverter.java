package com.SpringBoot.miniFlyway.converter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.SpringBoot.miniFlyway.dto.UserDto;
import com.SpringBoot.miniFlyway.entity.User;
import com.SpringBoot.miniFlyway.exception.LibraryException;
import com.SpringBoot.miniFlyway.interfaces.Mapper;
import com.SpringBoot.miniFlyway.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter implements Mapper<User, UserDto> {

    private final UserRepository userRepository;

    @Override
    public User convertToEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(findByNameOrByEmail(1, dto.getName()))
                .email(findByNameOrByEmail(2, dto.getEmail()))
                .age(dto.getAge())
                .build();
    }

    @Override
    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .age(entity.getAge())
                .build();
    }

    @Override
    public User update(UserDto dto, User existingEntity) {
        existingEntity.setName(dto.getName() != null ? dto.getName() : existingEntity.getName());
        existingEntity.setEmail(dto.getEmail() != null ? dto.getEmail() : existingEntity.getEmail());
        existingEntity.setAge(dto.getAge() != null ? dto.getAge() : existingEntity.getAge());
        return existingEntity;
    }

    private String findByNameOrByEmail(int a, String b) {
        String result = null;
        switch (a) {
            case 1 -> {
                if (userRepository.findByName(b).isPresent()) {
                    throw new LibraryException("USER002", "es", HttpStatus.CONFLICT);
                } else {
                    b = result;
                }
            }
            case 2 -> {
                if (userRepository.findByEmail(b).isPresent()) {
                    throw new LibraryException("USER002", "es", HttpStatus.CONFLICT);
                } else {
                    b = result;
                }
            }
            default ->
                throw new AssertionError();
        }
        return b;
    }

}
