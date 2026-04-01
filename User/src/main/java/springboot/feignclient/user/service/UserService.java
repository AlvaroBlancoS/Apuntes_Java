package springboot.feignclient.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springboot.feignclient.user.dto.UserDto;
import springboot.feignclient.user.entity.User;
import springboot.feignclient.user.mapper.Mapper;
import springboot.feignclient.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Mapper mapperUser;
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers(Sort sort) {
        List<User> users = userRepository.findAll(sort);
        return users.stream()
                .map(mapperUser::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapperUser.convertToDto(user);
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapperUser.convertToDto(user);
    }

    public UserDto createUser(UserDto dto) {
        boolean userExists = userRepository.findByName(dto.getName()).isPresent();
        if (userExists) {
            throw new RuntimeException("El usuario ya existe");
        }
        User user = mapperUser.convertToEntity(dto);
        User savedUser = userRepository.save(user);
        return mapperUser.convertToDto(savedUser);
    }

    public UserDto updateUser(UserDto dto) {
        User existingUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        User updatedUser = mapperUser.updateUser(dto, existingUser);
        User savedUser = userRepository.save(updatedUser);
        return mapperUser.convertToDto(savedUser);
    }

    public void deleteUserById(UUID id) {
        if (userExists(null, id, 2)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void deleteUserByName(String name) {
        if (userExists(name, null, 1)) {
            User user = userRepository.findByName(name).get();
            userRepository.delete(user);
        } else {
            throw new RuntimeException("Usuario no encontrado");
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

    /**
     * Asumiendo que el campo "password" se utiliza para almacenar el correo
     *  electrónico en este ejemplo, aunque en un caso real debería ser un campo separado.
     * @param name
     * @return
     */
    public String getUserEmailByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return user.getPassword();
    }

    /**
     * Asumiendo que el campo "password" se utiliza para almacenar el correo
     *  electrónico en este ejemplo, aunque en un caso real debería ser un campo separado.
     * @param id
     * @return
     */
    public String getUserEmailById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return user.getPassword();
    }
}
