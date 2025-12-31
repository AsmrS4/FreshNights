package com.project.pet.domain.requests;

import com.project.pet.domain.enums.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateEventRequest {
    private String title;
    private String description;
    private List<String> images;
    private EventStatus status;
    private LocalDateTime dateTime;
}
