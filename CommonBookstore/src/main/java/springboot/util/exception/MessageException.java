package springboot.util.exception;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum MessageException {

    USER_NOT_FOUND("user-1001", "User was not found", "Usuario no fue encontrado", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("user-1003", "User already exists", "Usuario ya existe", HttpStatus.CONFLICT),
    USER_INVALID("user-1003", "Invalid user data", "datos de usuario inválidos", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT("user-1004", "Current password is incorrect", "La contraseña actual no es correcta",
            HttpStatus.UNAUTHORIZED),
    PASSWORD_SAME("user-1005", "New password cannot be the same as the current password",
            "La nueva contraseña no puede ser igual a la contraseña actual", HttpStatus.CONFLICT),

    MAIL_NOT_FOUND("mail-1003", "Email was not found", "Correo electrónicono fue encontrado", HttpStatus.NOT_FOUND),
    MAIL_EXISTS("mail-1004", "Email already exists", "Correo electrónico ya existe", HttpStatus.CONFLICT),
    
    INTERNAL_ERROR("error.internal", "Error interno del servidor", "Error interno del servidor",
            HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String messageEn;
    private final String messageEs;
    private final HttpStatus status;

    MessageException(String code, String messageEN, String messageES, HttpStatus status) {
        this.code = code;
        this.messageEn = messageEN;
        this.messageEs = messageES;
        this.status = status;
    }

    public String getMessage(Locale locale) {
        if ("es".equalsIgnoreCase(locale.getLanguage())) {
            return messageEs;
        }
        return messageEn;
    }
}
