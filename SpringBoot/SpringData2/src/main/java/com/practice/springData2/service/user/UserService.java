package com.practice.springData2.service.user;

import java.util.List;

import com.practice.springData2.domain.user.UserDto;

public interface UserService {
	public UserDto create(final UserDto userDto);

	public UserDto getUser(final String username);

	public List<UserDto> getAllUsers();

	public UserDto update(final String username, final UserDto userDto);

	public void deleteByUsername(final String username);

}
