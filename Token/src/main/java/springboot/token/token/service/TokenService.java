package springboot.token.token.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springboot.dto.LoginUserDto;
import springboot.entity.User;
import springboot.token.token.dto.TokenResponseDto;
import springboot.token.token.entity.Token;
import springboot.token.token.entity.TokenType;
import springboot.token.token.repository.TokenRepository;
import springboot.token.token.repository.TokenUserRepository;
import springboot.util.exception.CustomException;
import springboot.util.exception.MessageException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final int TOKEN_BYTES = 48;
    private static final long EXPIRATION_HOURS = 2;

    private final TokenRepository tokenRepository;
    private final TokenUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    public TokenResponseDto login(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getUserName())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND_EN));

        if (!isPasswordValid(loginUserDto.getPassword(), user.getPassword())) {
            throw new CustomException(MessageException.USER_INVALID_EN);
        }

        LocalDateTime creationDate = LocalDateTime.now();
        Token savedToken = tokenRepository.save(Token.builder()
                .creationDate(creationDate)
                .expirationDate(creationDate.plusHours(EXPIRATION_HOURS))
                .userId(user.getId())
                .token(generateToken())
                .tokenType(TokenType.BEARER)
                .build());

        return TokenResponseDto.builder()
                .id(savedToken.getId())
                .creationDate(savedToken.getCreationDate())
                .expirationDate(savedToken.getExpirationDate())
                .userId(savedToken.getUserId())
                .token(savedToken.getToken())
                .tokenType(savedToken.getTokenType())
                .build();
    }

    private boolean isPasswordValid(String rawPassword, String storedPassword) {
        return passwordEncoder.matches(rawPassword, storedPassword) || rawPassword.equals(storedPassword);
    }

    private String generateToken() {
        byte[] bytes = new byte[TOKEN_BYTES];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
