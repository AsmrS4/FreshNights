package com.project.pet.domain.models;

import com.project.pet.domain.enums.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Event {
    private UUID id;
    private String title;
    private String description;
    private List<String> images;
    private EventStatus status;
    private LocalDateTime dateTime;
    private LocalDateTime createTime;
}
