package com.project.pet.helpers.mappers;

import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.enums.EventStatus;
import com.project.pet.domain.models.Event;
import com.project.pet.domain.requests.EventCreateRequest;
import com.project.pet.domain.requests.UpdateEventRequest;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventEntity mapToEntity(EventCreateRequest request) {
        EventEntity newEvent = new EventEntity();
        newEvent.setTitle(request.getTitle());
        newEvent.setDescription(request.getDescription());
        newEvent.setImages(request.getImages());
        newEvent.setDateTime(request.getDateTime());
        newEvent.setStatus(EventStatus.ACTIVE);

        return newEvent;
    }

    public Event mapToDto(EventEntity event) {
        Event newEventDto = new Event();
        newEventDto.setId(event.getId());
        newEventDto.setTitle(event.getTitle());
        newEventDto.setDescription(event.getDescription());
        newEventDto.setStatus(event.getStatus());
        newEventDto.setDateTime(event.getDateTime());
        newEventDto.setImages(event.getImages());
        newEventDto.setCreateTime(event.getCreateTime());

        return newEventDto;
    }

    public void mergeToEntity(EventEntity event, UpdateEventRequest request) {
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setImages(request.getImages());
        event.setStatus(request.getStatus());
        event.setDateTime(request.getDateTime());
    }
}
