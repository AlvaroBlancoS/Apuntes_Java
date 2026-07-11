package springboot.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum MessageException {

    USER_NOT_FOUND_EN("user-1001", "User was not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_ES("user-1001", "Usuario no fue encontrado", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS_EN("user-1003", "User already exists", HttpStatus.CONFLICT),
    USER_ALREADY_EXISTS_ES("user-1003", "Usuario ya existe", HttpStatus.CONFLICT),
    USER_INVALID_EN("user-1003", "Invalid user data", HttpStatus.BAD_REQUEST),
    USER_INVALID_ES("user-1003", "datos de usuario inválidos", HttpStatus.BAD_REQUEST),

    MAIL_NOT_FOUND_EN("mail-1003", "Mail was not found", HttpStatus.NOT_FOUND),
    MAIL_NOT_FOUND_ES("mail-1003", "Correo no fue encontrado", HttpStatus.NOT_FOUND),

    INTERNAL_ERROR("error.internal", "Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    MessageException(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
