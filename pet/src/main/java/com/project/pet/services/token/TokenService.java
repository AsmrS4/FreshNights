package com.project.pet.services.token;

import com.project.pet.domain.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
public abstract class TokenService {
    @Value("${jwt.secret-key}")
    protected String SECRET_KEY;

    public UUID getUserId(String token) {
        return UUID.fromString(getClaims(token).getId());
    }

    public boolean isTokenValid(String token, UserEntity user) {
        UUID tokenOwnerId = getUserId(token);
        UUID currentUserId = user.getId();
        return currentUserId.equals(tokenOwnerId) && !isTokenExpired(token);
    }

    protected boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);
        return expirationDate.before(new Date());
    }

    protected Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    protected Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            throw new JwtException(ex.getMessage());
        }
    }

    protected String generateToken(UserEntity userEntity, Duration lifeTime) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + lifeTime.toMillis());
        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .setId(String.valueOf(userEntity.getId()))
                .setSubject(userEntity.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    abstract public String createNewToken(UserEntity userEntity);
}
