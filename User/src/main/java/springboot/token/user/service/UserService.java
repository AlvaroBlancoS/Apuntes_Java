package springboot.token.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import springboot.dto.ChangePasswordDto;
import springboot.dto.LoginUserDto;
import springboot.dto.UserDto;
import springboot.entity.Mail;
import springboot.entity.User;
import springboot.mapper.MailMapper;
import springboot.mapper.UserMapper;
import springboot.repository.UserRepository;
import springboot.util.exception.CustomException;
import springboot.util.exception.MessageException;
import springboot.dto.MailDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MailMapper mailMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers(Sort sort) {
        List<User> users = userRepository.findAll(sort);
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));
        return userMapper.convertToDto(user);
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));
        return userMapper.convertToDto(user);
    }

    public MailDto getMailByUserId(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));

        MailDto mailRquest = mailMapper.convertToDto(mailMapper.getEntityById(user.getMailId()));
        return MailDto.builder()
                .id(mailRquest.getId())
                .mail(mailRquest.getMail())
                .description(mailRquest.getDescription())
                .build();
    }

    public String insertPassword(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getUserName())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));

        System.out.println("Contraseña proporcionada: " + loginUserDto.getPassword());
        boolean isValid = passwordEncoder.matches(
                loginUserDto.getPassword(),
                user.getPassword());

        if (!isValid) {
            throw new CustomException(MessageException.PASSWORD_INCORRECT);
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
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));

        String encodedPassword = passwordEncoder.encode(loginUserDto.getPassword());

        boolean isValid = passwordEncoder.matches(
                loginUserDto.getPassword(),
                user.getPassword());

        if (isValid) {
            throw new CustomException(MessageException.PASSWORD_SAME);
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
     * 
     * @param dto
     * @return
     */
    public String changePasswordSecure(ChangePasswordDto dto) {
        User user = userRepository.findByName(dto.getUserName())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));

        boolean currentPasswordMatches = passwordEncoder.matches(
                dto.getCurrentPassword(),
                user.getPassword());

        if (!currentPasswordMatches) {
            throw new CustomException(MessageException.PASSWORD_INCORRECT);
        }

        boolean samePassword = passwordEncoder.matches(
                dto.getNewPassword(),
                user.getPassword());

        if (samePassword) {
            throw new CustomException(MessageException.PASSWORD_SAME);
        }

        String encodedNewPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);

        return "Contraseña actualizada correctamente";
    }

    public UserDto createUser(UserDto dto) {
        boolean userExists = userRepository.findByName(dto.getName()).isPresent();
        if (userExists) {
            throw new CustomException(MessageException.USER_ALREADY_EXISTS);
        }

        User user = userMapper.convertToEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.convertToDto(savedUser);
    }

    public UserDto updateUser(UserDto dto) {
        User existingUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));
        User updatedUser = userMapper.updateEntity(dto, existingUser);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.convertToDto(savedUser);
    }

    public MailDto getUserEmailByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));
        Mail mailRquest = mailMapper.getEntityById(user.getMailId());
        return MailDto.builder()
                .id(mailRquest.getId())
                .mail(mailRquest.getMail())
                .description(mailRquest.getDescription())
                .build();
    }

    public UserDto getUserByEmail(String email) {
        Mail mail = mailMapper.getEntityByName(email);
        User user = userRepository.findByMailId(mail.getId())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));
        return userMapper.convertToDto(user);
    }

    public void deleteUserById(UUID id) {
        if (userExists(null, id, 2)) {
            userRepository.deleteById(id);
        } else {
            throw new CustomException(MessageException.USER_NOT_FOUND);
        }
    }

    public void deleteUserByName(String name) {
        if (userExists(name, null, 1)) {
            User user = userRepository.findByName(name).get();
            userRepository.delete(user);
        } else {
            throw new CustomException(MessageException.USER_NOT_FOUND);
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
