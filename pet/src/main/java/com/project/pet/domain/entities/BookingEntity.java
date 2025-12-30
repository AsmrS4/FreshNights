package com.project.pet.domain.entities;

import com.project.pet.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ownerId", "eventId"})}
)
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BookingStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EventEntity event;
    private LocalDateTime createTime = LocalDateTime.now();
}
