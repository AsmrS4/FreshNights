package com.project.pet.services.event;

import com.project.pet.domain.models.Event;
import com.project.pet.domain.requests.EventCreateRequest;
import com.project.pet.domain.requests.UpdateEventRequest;

import java.util.List;
import java.util.UUID;

public interface EventService {
    Event createEvent(EventCreateRequest request);
    Event retrieveEventDetails(UUID eventId);
    List<Event> retrieveEvents();
    List<Event> retrieveArchiveEvents();
    Event updateEventDetails(UUID eventId, UpdateEventRequest request);
    boolean cancelEvent(UUID eventId);
}
