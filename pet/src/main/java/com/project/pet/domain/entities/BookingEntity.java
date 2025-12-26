package com.project.pet.domain.entities;

import com.project.pet.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BookingStatus status;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;
    private LocalDateTime createTime = LocalDateTime.now();
}
