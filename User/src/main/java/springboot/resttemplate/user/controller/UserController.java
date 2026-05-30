package springboot.resttemplate.user.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import springboot.resttemplate.user.dto.ChangePasswordDto;
import springboot.resttemplate.user.dto.LoginUserDto;
import springboot.resttemplate.user.dto.UserDto;
import springboot.resttemplate.user.service.UserService;

import com.commonbookstore.dto.MailDto;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados, ordenados por ID ascendente o descendente")
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "id,asc") String sort) {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1]) ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sortObj = Sort.by(direction, sortParams[0]);
        return userService.getAllUsers(sortObj);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Obtener un usuario por nombre", description = "Devuelve el usuario correspondiente al nombre proporcionado")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        UserDto user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve el usuario correspondiente al ID proporcionado")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login/secure")
    @Operation(summary = "Iniciar sesión de usuario de forma más segura", description = "Ejemplo recomendado de login usando POST y RequestBody para no exponer la contraseña en la URL")
    public ResponseEntity<String> loginUserSecure1(@RequestBody @Valid LoginUserDto loginUserDto) {
        String response = userService.insertPassword(loginUserDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/changePassword/secure")
    @Operation(summary = "Cambiar la contraseña de un usuario de forma más segura", description = "Permite a un usuario actualizar su contraseña proporcionando su nombre, contraseña actual y nueva contraseña, con validaciones adicionales para mayor seguridad")
    public ResponseEntity<String> updatePasswordSecure(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        String response = userService.changePasswordSecure(changePasswordDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea y guarda un nuevo usuario en el sistema")
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto dto) {
        UserDto createdUser = userService.createUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping
    @Operation(summary = "Actualizar un usuario", description = "Actualiza la información de un usuario existente")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated UserDto dto) {
        UserDto updatedUser = userService.updateUser(dto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/name/{name}/email")
    @Operation(summary = "Obtener el correo electrónico de un usuario por nombre", description = "Devuelve el correo electrónico de un usuario específico basado en su nombre")
    public ResponseEntity<MailDto> getUserEmailByName(@PathVariable String name) {
        MailDto userEmail = userService.getUserEmailByName(name);
        return ResponseEntity.ok(userEmail);
    }

    @GetMapping("/{id}/email")
    @Operation(summary = "Obtener el correo electrónico de un usuario por ID", description = "Devuelve el correo electrónico de un usuario específico basado en su ID")
    public ResponseEntity<MailDto> getUserEmailById(@PathVariable UUID id) {
        MailDto email = userService.getMailByUserId(id);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/{id}/username")
    @Operation(summary = "Obtener el nombre de un usuario por ID", description = "Devuelve el nombre de un usuario específico basado en su ID")
    public ResponseEntity<String> getUserNameById(@PathVariable UUID id) {
        String name = userService.getUserById(id).getName();
        return ResponseEntity.ok(name);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Obtener el nombre de un usuario por correo electrónico", description = "Devuelve el nombre de un usuario específico basado en su correo electrónico")
    public ResponseEntity<UserDto> getUserNameByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario específico basado en su ID")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    @Operation(summary = "Eliminar un usuario por nombre", description = "Elimina un usuario específico basado en su nombre")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }

}
