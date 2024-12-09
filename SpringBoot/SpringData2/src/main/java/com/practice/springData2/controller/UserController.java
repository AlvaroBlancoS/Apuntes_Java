package com.practice.springData2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.springData2.domain.user.UserDto;
import com.practice.springData2.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	@Operation(description = "Create user", summary = "Create user")
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
		UserDto createUser = userService.create(userDto);
		return ResponseEntity.ok(createUser);
	}

	@GetMapping("/getUser/{userName}")
	@Operation(description = "Create user", summary = "Create user")
	public ResponseEntity<UserDto> getUserByNameuser(@PathVariable("userName") String userName) {
		UserDto userDto = userService.getUser(userName);
		return ResponseEntity.ok(userDto);
	}

	@GetMapping
	@Operation(description = "", summary = "")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PutMapping("/updateUser/{userName}")
	@Operation(description = "", summary = "")
	public ResponseEntity<UserDto> updateUser(@PathVariable("UserName") String userName,
			@Valid @RequestBody UserDto userDto) {
		UserDto updateUser = userService.update(userName, userDto);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/updateUser/{userName}")
	@Operation(description = "", summary = "")
	public ResponseEntity<Void> deleteProductByName(@PathVariable("userName") String userName) {
		userService.deleteByUsername(userName);
		return ResponseEntity.noContent().build();
	}

}
