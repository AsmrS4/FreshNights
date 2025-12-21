package com.project.pet.controllers;

import com.project.pet.domain.requests.CreateAccountRequest;
import com.project.pet.domain.requests.LoginRequest;
import com.project.pet.domain.requests.RefreshSessionRequest;
import com.project.pet.domain.responses.AuthResponse;
import com.project.pet.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;
    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid LoginRequest request) throws BadRequestException, IllegalAccessException {
        log.info("RECEIVED REQUEST: {}", request);
        return ResponseEntity.ok(authService.loginUser(request));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> createUserAccount(@RequestBody @Valid CreateAccountRequest request) throws BadRequestException {
        return ResponseEntity.ok(authService.createUserAccount(request));
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshUserSession(@RequestBody @Valid RefreshSessionRequest request) {
        return ResponseEntity.ok(authService.refreshSession(request));
    }
}
