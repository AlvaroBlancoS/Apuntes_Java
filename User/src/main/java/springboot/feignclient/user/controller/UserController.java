package springboot.feignclient.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.user.dto.UserDto;
import springboot.feignclient.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "id,asc") String[] sort) {
        Sort sortObj = Sort.by(sort);
        List<UserDto> users = userService.getAllUsers(sortObj);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        UserDto user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto dto) {
        UserDto createdUser = userService.createUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated UserDto dto) {
        UserDto updatedUser = userService.updateUser(dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/name/{name}/email")
    public ResponseEntity<String> getUserEmailByName(@PathVariable String name) {
        String userEmail = userService.getUserEmailByName(name);
        return ResponseEntity.ok(userEmail);
    }

    @GetMapping("/name/{name}/name")
    public ResponseEntity<String> getUserNameByName(@PathVariable String name) {
        String userName = userService.getUserByName(name).getName();
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<String> getUserEmailById(@PathVariable UUID id) {
        String email = userService.getUserEmailById(id);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/{id}/mail")
    public ResponseEntity<String> getUserNameById(@PathVariable UUID id) {
        String name = userService.getUserById(id).getName();
        return ResponseEntity.ok(name);
    }

}
