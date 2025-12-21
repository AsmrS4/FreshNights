package com.project.pet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> retrieveUserProfile() {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUserProfile() {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> updateUserPassword() {
        return ResponseEntity.ok(null);
    }
}
