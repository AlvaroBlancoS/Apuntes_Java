package springboot.resttemplate.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import springboot.resttemplate.user.dto.ChangePasswordDto;
import springboot.resttemplate.user.dto.LoginUserDto;
import springboot.resttemplate.user.dto.UserDto;
import springboot.resttemplate.user.entity.User;
import springboot.resttemplate.user.mapper.Mapper;
import springboot.resttemplate.user.repository.UserRepository;

import com.commonbookstore.dto.MailDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Mapper mapperUser;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers(Sort sort) {
        List<User> users = userRepository.findAll(sort);
        return users.stream()
                .map(mapperUser::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return mapperUser.convertToDto(user);
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return mapperUser.convertToDto(user);
    }

    public MailDto getMailByUserId(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        MailDto mailRquest = mapperUser.getMailById(user.getMailId());
        return MailDto.builder()
                .id(mailRquest.getId())
                .mail(mailRquest.getMail())
                .description(mailRquest.getDescription())
                .createdTime(mailRquest.getCreatedTime())
                .updatedTime(mailRquest.getUpdatedTime())
                .build();
    }

    public String insertPassword(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getUserName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        System.out.println("Contraseña proporcionada: " + loginUserDto.getPassword());
        boolean isValid = passwordEncoder.matches(
                loginUserDto.getPassword(),
                user.getPassword());

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return "Login exitoso";
    }

    /**
     * No es recomendable usar el mismo endpoint para login y cambio de contraseña,
     * ya que ambos requieren la contraseña actual para validar la identidad del
     * usuario.
     * 
     * @param loginUserDto
     * @return
     */
    public LoginUserDto changePassword(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getUserName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        String encodedPassword = passwordEncoder.encode(loginUserDto.getPassword());

        boolean isValid = passwordEncoder.matches(
                loginUserDto.getPassword(),
                user.getPassword());

        if (isValid) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La nueva contraseña no puede ser igual a la contraseña actual");
        }
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return LoginUserDto.builder()
                .userName(user.getName())
                .password(encodedPassword)
                .build();
    }
    /**
     * Este método es una versión mejorada del cambio de contraseña, 
     * que incluye validaciones adicionales para garantizar 
     * la seguridad y la integridad de la operación.
     * @param dto
     * @return
     */
    public String changePasswordSecure(ChangePasswordDto dto) {
        User user = userRepository.findByName(dto.getUserName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        boolean currentPasswordMatches = passwordEncoder.matches(
                dto.getCurrentPassword(),
                user.getPassword());

        if (!currentPasswordMatches) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "La contraseña actual no es correcta");
        }

        boolean samePassword = passwordEncoder.matches(
                dto.getNewPassword(),
                user.getPassword());

        if (samePassword) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "La nueva contraseña no puede ser igual a la actual");
        }

        String encodedNewPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);

        return "Contraseña actualizada correctamente";
    }

    public UserDto createUser(UserDto dto) {
        boolean userExists = userRepository.findByName(dto.getName()).isPresent();
        if (userExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
        }

        User user = mapperUser.convertToEntity(dto);
        User savedUser = userRepository.save(user);
        return mapperUser.convertToDto(savedUser);
    }

    public UserDto updateUser(UserDto dto) {
        User existingUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        User updatedUser = mapperUser.updateUser(dto, existingUser);
        User savedUser = userRepository.save(updatedUser);
        return mapperUser.convertToDto(savedUser);
    }

    public MailDto getUserEmailByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        MailDto mailRquest = mapperUser.getMailById(user.getMailId());
        return MailDto.builder()
                .id(mailRquest.getId())
                .mail(mailRquest.getMail())
                .description(mailRquest.getDescription())
                .createdTime(mailRquest.getCreatedTime())
                .updatedTime(mailRquest.getUpdatedTime())
                .build();
    }

    public UserDto getUserByEmail(String email) {
        MailDto mail = mapperUser.getMailByName(email);
        User user = userRepository.findByMailId(mail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return mapperUser.convertToDto(user);
    }

    public void deleteUserById(UUID id) {
        if (userExists(null, id, 2)) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }

    public void deleteUserByName(String name) {
        if (userExists(name, null, 1)) {
            User user = userRepository.findByName(name).get();
            userRepository.delete(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }

    private boolean userExists(String name, UUID id, int option) {
        if (option == 1) {
            return userRepository.findByName(name).isPresent();
        } else if (option == 2) {
            return userRepository.findById(id).isPresent();
        }
        return false;
    }

}
