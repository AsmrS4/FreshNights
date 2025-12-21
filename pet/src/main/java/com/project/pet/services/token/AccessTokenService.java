package com.project.pet.services.token;

import com.project.pet.domain.entities.UserEntity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@Setter
public class AccessTokenService extends TokenService {
    @Value("${jwt.access-lifetime}")
    private Duration ACCESS_LIFETIME;
    @Override
    public String createNewToken(UserEntity userEntity) {
        return generateToken(userEntity, ACCESS_LIFETIME);
    }
}
