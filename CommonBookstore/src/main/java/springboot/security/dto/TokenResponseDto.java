package springboot.security.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.security.entity.TokenType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDto {

    private UUID id;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private UUID userId;
    private String token;
    private TokenType tokenType;
}
