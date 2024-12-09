package com.practice.springData2.service.user;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.practice.springData2.domain.user.User;
import com.practice.springData2.domain.user.UserDto;
import com.practice.springData2.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserDto create(UserDto userDto) {
		String username = userDto.getUsername();
		userRepository.findByUsername(username).ifPresent(u -> {
			throw new RuntimeException(username + " not exist");
		});
		
		if (!isValid(userDto.getPassword())) {
			throw new RuntimeException("Contraseña inválida");
		}

		User user = mapToEntity(userDto, new User());
		User savedUser = userRepository.save(user);
		return mapToDto(savedUser, new UserDto());
	}

	@Override
	public UserDto getUser(final String username) {
		return userRepository.findByUsername(username).map(user -> mapToDto(user, new UserDto()))
				.orElseThrow(() -> new RuntimeException(username + " not exist"));
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> seeUsers = userRepository.findAll();
		return seeUsers.stream().map(user -> mapToDto(user, new UserDto())).toList();
	}

	@Override
	public UserDto update(String username, UserDto userDto) {
		final User updateUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException(username + " not exist"));
		// Crear una validacion de password
		mapToEntity(userDto, updateUser);
		User addUser = userRepository.save(updateUser);
		return mapToDto(addUser, userDto);
	}

	@Override
	public void deleteByUsername(String username) {
		if (existUsername(username)) {
			userRepository.deleteByUsername(username);
		} else {
			throw new RuntimeException(username + " not exist");
		}

	}

	public boolean existUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public UserDto mapToDto(final User user, final UserDto userDto) {
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		return userDto;

	}

	public User mapToEntity(final UserDto userDto, final User user) {
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		return user;
	}

    public boolean isValid(String password) {
        if (password.length() < 8) return false;
        if (!Pattern.compile("[a-z]").matcher(password).find()) return false;
        if (!Pattern.compile("[0-9]").matcher(password).find()) return false;
        if (!Pattern.compile("[@#$%^&*.,!?]").matcher(password).find()) return false;
        return true;
    }

}
