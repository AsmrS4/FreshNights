package com.project.pet.domain.models;

import com.project.pet.domain.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private UUID id;
    private BookingStatus status;
    private UserShort owner;
    private EventShort event;
    private LocalDateTime createTime;
}
