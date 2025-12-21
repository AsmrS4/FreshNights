package com.project.pet.domain.entities;

import com.project.pet.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String hashPassword;
    private boolean isBlocked;
    private LocalDateTime accountCreateTime = LocalDateTime.now();
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private TokenEntity refreshToken;
}
