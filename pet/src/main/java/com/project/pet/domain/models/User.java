package com.project.pet.domain.models;

import com.project.pet.domain.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private LocalDateTime accountCreateTime;
}
