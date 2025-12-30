package com.project.pet.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "reviews",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"authorId", "eventId"})
})
@Builder
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String text;
    private int rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EventEntity event;
    private LocalDateTime createTime = LocalDateTime.now();
}
