package com.project.pet.services.token;

import com.project.pet.dao.RefreshTokenRepository;
import com.project.pet.domain.entities.TokenEntity;
import com.project.pet.domain.entities.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@Setter
public class RefreshTokenService extends TokenService{
    @Value("${jwt.refresh-lifetime}")
    private Duration REFRESH_LIFETIME;
    private String previousRefreshToken = null;
    private final RefreshTokenRepository tokenRepository;

    public RefreshTokenService(@Autowired RefreshTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String createNewToken(UserEntity userEntity) {
        if(previousRefreshToken != null && !isTokenValid(previousRefreshToken, userEntity)) {
            throw new JwtException("Невалидный refresh токен");
        }

        String newRefreshToken = generateToken(userEntity, REFRESH_LIFETIME);
        deletePreviousRefreshToken(previousRefreshToken);
        saveNewRefreshToken(newRefreshToken, userEntity);

        return newRefreshToken;
    }

    private void saveNewRefreshToken(String newRefreshToken, UserEntity owner) {
        TokenEntity newRefreshTokenEntity = new TokenEntity();
        TokenEntity prevRefreshTokenEntity = tokenRepository.findByOwner(owner).orElse(null);
        if(prevRefreshTokenEntity != null) {
            prevRefreshTokenEntity.setPayload(newRefreshToken);
            tokenRepository.save(prevRefreshTokenEntity);

        } else {
            newRefreshTokenEntity.setPayload(newRefreshToken);
            newRefreshTokenEntity.setOwner(owner);
            tokenRepository.save(newRefreshTokenEntity);
        }
    }

    private void deletePreviousRefreshToken(String previousRefreshToken) {
        tokenRepository.findByPayload(previousRefreshToken)
                .ifPresent(token -> {
                    token.setPayload(previousRefreshToken);
                    tokenRepository.save(token);
                });
    }
}
