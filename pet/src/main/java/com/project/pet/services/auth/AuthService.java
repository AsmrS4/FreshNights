package com.project.pet.services.auth;

import com.project.pet.domain.requests.CreateAccountRequest;
import com.project.pet.domain.requests.LoginRequest;
import com.project.pet.domain.requests.RefreshSessionRequest;
import com.project.pet.domain.responses.AuthResponse;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    AuthResponse loginUser(LoginRequest request) throws BadRequestException, IllegalAccessException;
    AuthResponse createUserAccount(CreateAccountRequest request) throws BadRequestException;
    AuthResponse createStaffAccount(CreateAccountRequest request) throws BadRequestException;
    AuthResponse createAdminAccount(CreateAccountRequest request) throws BadRequestException;
    AuthResponse refreshSession(RefreshSessionRequest request);
}
