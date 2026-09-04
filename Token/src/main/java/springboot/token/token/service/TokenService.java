package springboot.token.token.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springboot.dto.LoginEmailDto;
import springboot.dto.LoginUserDto;
import springboot.entity.Mail;
import springboot.entity.User;
import springboot.repository.EmailRepository;
import springboot.repository.UserRepository;
import springboot.security.dto.TokenResponseDto;
import springboot.security.entity.Token;
import springboot.security.entity.TokenType;
import springboot.security.repository.TokenRepository;
import springboot.util.exception.CustomException;
import springboot.util.exception.MessageException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final int TOKEN_BYTES = 48;
    private static final long EXPIRATION_HOURS = 2;

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * 
     * @param loginUserDto
     * @return TokenResponseDto
     * @throws CustomException if the user is not found or the password is invalid
     * @throws MessageException if the user is not found or the password is invalid
     */
    @SuppressWarnings("null")
    public TokenResponseDto login(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getUserName())
                .orElseThrow(() -> new CustomException(MessageException.USER_NOT_FOUND));

        if (!isPasswordValid(loginUserDto.getPassword(), user.getPassword())) {
            throw new CustomException(MessageException.USER_INVALID);
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


        @SuppressWarnings("null")
    public TokenResponseDto loginByEmail(LoginEmailDto loginEmailDto) {
        Mail mail = emailRepository.findByMail(loginEmailDto.getMail())
                .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
                
        Optional<User> optionalUser = userRepository.findByMailId(mail.getId());

        if (!optionalUser.isPresent()) {
            throw new CustomException(MessageException.USER_NOT_FOUND);
        }

        User user = optionalUser.get();

        if (!isPasswordValid(loginEmailDto.getPassword(), user.getPassword())) {
            throw new CustomException(MessageException.USER_INVALID);
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

    /**
     * 
     * @param rawPassword
     * @param storedPassword
     * @return true if the password is valid, false otherwise
     */
    private boolean isPasswordValid(String rawPassword, String storedPassword) {
        return passwordEncoder.matches(rawPassword, storedPassword) || rawPassword.equals(storedPassword);
    }

    /**
     * Generates a random token.
     * @return the generated token
     */
    private String generateToken() {
        byte[] bytes = new byte[TOKEN_BYTES];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    //Borrar los tokens que hayan expirado
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        tokenRepository.deleteAllByExpirationDateBefore(now);  
    
    }
}
