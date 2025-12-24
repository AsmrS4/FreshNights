package com.project.pet.services.event;

import com.project.pet.dao.EventRepository;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.enums.EventStatus;
import com.project.pet.domain.models.Event;
import com.project.pet.domain.requests.EventCreateRequest;
import com.project.pet.domain.requests.UpdateEventRequest;
import com.project.pet.helpers.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    @Override
    public Event createEvent(EventCreateRequest request) {
        EventEntity newEvent = eventMapper.mapToEntity(request);
        saveEvent(newEvent);
        return eventMapper.mapToDto(newEvent);
    }

    @Override
    public Event retrieveEventDetails(UUID eventId) {
        EventEntity event = findEventById(eventId);
        return eventMapper.mapToDto(event);
    }

    @Override
    public List<Event> retrieveEvents() {
        List<EventEntity> events = eventRepository.findAllActive();
        return events.stream().map(eventMapper::mapToDto).toList();
    }

    @Override
    public List<Event> retrieveArchiveEvents() {
        return List.of();
    }

    @Override
    public Event updateEventDetails(UUID eventId, UpdateEventRequest request) {
        EventEntity event = findEventById(eventId);
        eventMapper.mergeToEntity(event, request);
        saveEvent(event);
        return eventMapper.mapToDto(event);
    }

    @Override
    public boolean cancelEvent(UUID eventId) {
        EventEntity event = findEventById(eventId);
        event.setStatus(EventStatus.CANCELED);
        saveEvent(event);
        return true;
    }

    private EventEntity findEventById(UUID eventId) {
        return eventRepository.findEventById(eventId).orElseThrow(
                () -> new NoSuchElementException("Событие не найдено")
        );
    }

    private void saveEvent(EventEntity event) {
        eventRepository.save(event);
    }
}
