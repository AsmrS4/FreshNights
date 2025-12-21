package com.project.pet.services.auth;

import com.project.pet.dao.UserRepository;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.requests.CreateAccountRequest;
import com.project.pet.domain.requests.LoginRequest;
import com.project.pet.domain.requests.RefreshSessionRequest;
import com.project.pet.domain.responses.AuthResponse;
import com.project.pet.helpers.mappers.UserMapper;
import com.project.pet.services.token.AccessTokenService;
import com.project.pet.services.token.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public AuthResponse loginUser(LoginRequest request) throws BadRequestException, IllegalAccessException {
        UserEntity user = findUserByEmail(request.getEmail());
        log.info("RECEIVED REQUEST: {}", request);
        if(isPasswordCorrect(request.getPassword(), user.getHashPassword())) {
            throw new BadRequestException("Неверный пароль");
        }
        if(user.isBlocked()) {
            throw new IllegalAccessException("Ваш аккаунт забллокирован");
        }
        log.info("RECEIVED USER: {}", user);
        return getTokenPair(user);
    }

    @Override
    public AuthResponse createUserAccount(CreateAccountRequest request) throws BadRequestException {
        if(existByEmail(request.getEmail())) {
            throw new BadRequestException("Пользователь с таким email уже существует");
        }
        userMapper.setPasswordEncoder(passwordEncoder);
        UserEntity user = userRepository.save(userMapper.mapToNewUser(request));

        return getTokenPair(user);
    }

    @Override
    public AuthResponse refreshSession(RefreshSessionRequest request) {
        UUID userId = refreshTokenService.getUserId(request.getRefresh());
        UserEntity user = findUserById(userId);
        refreshTokenService.setPreviousRefreshToken(request.getRefresh());
        return getTokenPair(user);
    }

    private boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isPasswordCorrect(String password, String hashPassword) {
//        return passwordEncoder.matches(password, hashPassword);
        return Objects.equals(password, hashPassword);
    }

    private AuthResponse getTokenPair(UserEntity user) {
        String accessToken = accessTokenService.createNewToken(user);
        String refreshToken = refreshTokenService.createNewToken(user);
        return new AuthResponse(accessToken, refreshToken);
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
    }

    private UserEntity findUserById(UUID userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
    }
}
