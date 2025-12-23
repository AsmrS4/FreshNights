package com.project.pet.controllers;

import com.project.pet.domain.requests.ChangePasswordRequest;
import com.project.pet.domain.requests.UpdateProfileRequest;
import com.project.pet.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> retrieveUserProfile() {
        return ResponseEntity.ok(userService.retrieveUserProfile());
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUserProfile(@RequestBody @Valid UpdateProfileRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.updateUserProfile(request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody @Valid ChangePasswordRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.changeUserPassword(request));
    }

    @DeleteMapping("/{userId}/block")
    public ResponseEntity<?> blockUserAccount(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.changeUserBlockStatus(userId, true));
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<?> unblockUserAccount(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.changeUserBlockStatus(userId, false));
    }
}
