package com.project.pet.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "refresh")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String payload;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
}
