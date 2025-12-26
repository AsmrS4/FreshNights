package com.project.pet.services.booking;

import com.project.pet.domain.models.Booking;
import com.project.pet.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking retrieveBookingDetails(UUID bookingId);
    Booking createBooking(UUID eventId);
    Booking cancelBooking(UUID bookingId);
    List<User> retrieveGuestsOnEvent(UUID eventId);
    List<Booking> retrieveAllBookings();
}
