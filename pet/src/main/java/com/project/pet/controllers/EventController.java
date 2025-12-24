package com.project.pet.controllers;

import com.project.pet.domain.requests.EventCreateRequest;
import com.project.pet.domain.requests.UpdateEventRequest;
import com.project.pet.services.event.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "bearerAuth")
public class EventController {
    private final EventService eventService;

    public EventController(@Autowired EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody @Valid EventCreateRequest request) {
        return ResponseEntity.ok(eventService.createEvent(request));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> retrieveEventDetails(@PathVariable UUID eventId) {
        return ResponseEntity.ok(eventService.retrieveEventDetails(eventId));
    }
    //TODO: добавить фильтрацию и пагинацию
    @GetMapping
    public ResponseEntity<?> retrieveEvents() {
        return ResponseEntity.ok(eventService.retrieveEvents());
    }
    //TODO: добавить пагинацию для архива
    @GetMapping("/archive")
    public ResponseEntity<?> retrieveArchivedEvents() {
        return ResponseEntity.ok(eventService.retrieveArchiveEvents());
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEventDetails(@PathVariable UUID eventId, @RequestBody @Valid UpdateEventRequest request) {
        return ResponseEntity.ok(eventService.updateEventDetails(eventId, request));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> cancelEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(eventService.cancelEvent(eventId));
    }
}
