package springboot.token.token.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import springboot.dto.LoginUserDto;
import springboot.token.token.dto.TokenResponseDto;
import springboot.token.token.service.TokenService;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesion", description = "Valida usuario y contrasena, genera un token Bearer y lo guarda con fecha de caducidad")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginUserDto loginUserDto) {
        return ResponseEntity.ok(tokenService.login(loginUserDto));
    }
}
