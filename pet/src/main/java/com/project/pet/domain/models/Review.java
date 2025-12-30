package com.project.pet.domain.models;

import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Review {
    private UUID id;
    private String text;
    private int rating;
    private UserShort author;
    private LocalDateTime createTime;
}
