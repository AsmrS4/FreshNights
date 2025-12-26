package com.project.pet.services.booking;

import com.project.pet.dao.BookingRepository;
import com.project.pet.dao.EventRepository;
import com.project.pet.domain.entities.BookingEntity;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.enums.BookingStatus;
import com.project.pet.domain.models.Booking;
import com.project.pet.domain.models.User;
import com.project.pet.helpers.mappers.BookingMapper;
import com.project.pet.helpers.user.UserContextManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking retrieveBookingDetails(UUID bookingId) {
        BookingEntity booking = findBookingById(bookingId);
        return bookingMapper.mapToDto(booking);
    }

    @Override
    public Booking createBooking(UUID eventId) {
        EventEntity event = findEventById(eventId);
        UserEntity user = UserContextManager.getUserFromContext();
        BookingEntity newBooking = bookingMapper.mapToNewEntity(user, event);
        bookingRepository.save(newBooking);
        return bookingMapper.mapToDto(newBooking);
    }

    @Override
    public Booking cancelBooking(UUID bookingId) {
        BookingEntity booking = findBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);
        return bookingMapper.mapToDto(booking);
    }

    @Override
    public List<User> retrieveGuestsOnEvent(UUID eventId) {
        return List.of();
    }

    @Override
    public List<Booking> retrieveAllBookings() {
        return List.of();
    }

    private BookingEntity findBookingById(UUID bookingId) {
        return bookingRepository.findBookingById(bookingId).orElseThrow(
                () -> new NoSuchElementException("Бронь не найдена")
        );
    }
    private EventEntity findEventById(UUID eventId) {
        return eventRepository.findEventById(eventId).orElseThrow(
                () -> new NoSuchElementException("Мероприятие не найдено")
        );
    }
}
