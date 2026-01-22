package com.SpringBoot.miniFlyway.exception;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryException extends RuntimeException {

    private final String errorCode;
    private final String language;
    private final HttpStatus status;

    public LibraryException(String message) {
        super(message);
        this.errorCode = null;
        this.language = null;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public LibraryException(String message, HttpStatus status) {
        super(message);
        this.errorCode = null;
        this.language = null;
        this.status = status;
    }

    public LibraryException(String errorCode, String language, HttpStatus status) {
        super(getDescription(errorCode, language));
        this.errorCode = errorCode;
        this.language = language;
        this.status = status;
    }

    public LibraryException(String message, int status) {
        super(message);
        HttpStatus httpStatus = HttpStatus.resolve(status);
        this.errorCode = null;
        this.language = null;
        this.status = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public static String getDescription(String codigo, String idioma) {
        String rutaArchivo = "/errores.properties";

        try {
            Properties properties = new Properties();
            InputStream input = LibraryException.class.getResourceAsStream(rutaArchivo);

            properties.load(input);
            return properties.getProperty(codigo + "." + idioma, "Unknown error code");
        } catch (Exception e) {
            return "Error getting the descriptions.";
        }
    }

}
