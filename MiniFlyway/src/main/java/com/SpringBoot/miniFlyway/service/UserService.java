package com.SpringBoot.miniFlyway.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SpringBoot.miniFlyway.converter.UserConverter;
import com.SpringBoot.miniFlyway.dto.UserDto;
import com.SpringBoot.miniFlyway.entity.User;
import com.SpringBoot.miniFlyway.exception.LibraryException;
import com.SpringBoot.miniFlyway.interfaces.BasicService;
import com.SpringBoot.miniFlyway.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements BasicService<UserDto, Integer> {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public List<UserDto> getAll() {
        List<User> result = userRepository.findAll(Sort.by("id"));
        if (result.isEmpty()) {
            throw new LibraryException("No hay ningún usuario");
        }
        return result.stream().map(userConverter::convertToDto).toList();
    }

    @Override
    public UserDto getById(Integer id) {
        return userRepository.findById(id).map(userConverter::convertToDto)
                .orElseThrow(() -> new LibraryException(""));
    }

    @Override
    public UserDto save(UserDto dto) {
        User user = userConverter.convertToEntity(dto);
        User userSave = userRepository.save(user);
        return userConverter.convertToDto(userSave);
    }

    @Override
    public UserDto update(UserDto dto) {
        User userEntity = userRepository.findById(dto.getId())
                .orElseThrow(() -> new LibraryException(""));
        User userUpdate = userConverter.update(dto, userEntity);
        return userConverter.convertToDto(userUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new LibraryException("Not supported yet.");
        }
    }
}
