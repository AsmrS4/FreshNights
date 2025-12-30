package com.project.pet.controllers;

import com.project.pet.services.booking.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class BookingController {
    private final BookingService bookingService;
    public BookingController(@Autowired BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/{eventId}")
    public ResponseEntity<?> createBookingOnEvent(@PathVariable UUID eventId) throws BadRequestException {
        return ResponseEntity.ok(bookingService.createBooking(eventId));
    }
    @GetMapping
    //TODO: добавить фильтрацию
    public ResponseEntity<?> retrieveAllBookings() {
        return ResponseEntity.ok(bookingService.retrieveAllBookings());
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> retrieveBooking(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.retrieveBookingDetails(bookingId));
    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBookingOnEvent(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @GetMapping("/{eventId}/guests")
    public ResponseEntity<?> retrieveGuestsOnEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(bookingService.retrieveGuestsOnEvent(eventId));
    }
}
