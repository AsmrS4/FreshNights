package com.project.pet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @PostMapping("/{eventId}")
    public ResponseEntity<?> createBookingOnEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(null);
    }
    @GetMapping
    //TODO: добавить фильтрацию
    public ResponseEntity<?> retrieveAllBookings() {
        return ResponseEntity.ok(null);
    }
    @GetMapping("/bookingId")
    public ResponseEntity<?> retrieveBooking(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(null);
    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBookingOnEvent(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{eventId}/guests")
    public ResponseEntity<?> retrieveGuestsOnEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(null);
    }
}
