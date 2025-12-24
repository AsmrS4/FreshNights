package com.project.pet.services.event;

import com.project.pet.dao.EventRepository;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.models.Event;
import com.project.pet.domain.requests.EventCreateRequest;
import com.project.pet.domain.requests.UpdateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    public EventServiceImpl(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public Event createEvent(EventCreateRequest request) {
        return null;
    }

    @Override
    public Event retrieveEventDetails(UUID eventId) {
        return null;
    }

    @Override
    public List<Event> retrieveEvents() {
        return List.of();
    }

    @Override
    public List<Event> retrieveArchiveEvents() {
        return List.of();
    }

    @Override
    public Event updateEventDetails(UUID eventId, UpdateEventRequest request) {
        return null;
    }

    @Override
    public boolean cancelEvent(UUID eventId) {
        return false;
    }

    private EventEntity findEventById(UUID eventId) {
        return eventRepository.findEventById(eventId).orElseThrow(
                () -> new NoSuchElementException("Событие не найдено")
        );
    }

    private EventEntity saveEvent(EventEntity event) {
        return eventRepository.save(event);
    }
}
