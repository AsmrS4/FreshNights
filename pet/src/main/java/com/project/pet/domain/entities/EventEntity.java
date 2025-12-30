package com.project.pet.domain.entities;

import com.project.pet.domain.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private List<String> images;
    private EventStatus status = EventStatus.ACTIVE;
    private LocalDateTime dateTime;
    private LocalDateTime createTime = LocalDateTime.now();
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingEntity> bookings;
}
