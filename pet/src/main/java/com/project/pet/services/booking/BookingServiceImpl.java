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
import com.project.pet.helpers.mappers.UserMapper;
import com.project.pet.helpers.user.UserContextManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;

    @Override
    public Booking retrieveBookingDetails(UUID bookingId) {
        BookingEntity booking = findBookingById(bookingId);
        return bookingMapper.mapToDto(booking);
    }

    @Override
    public Booking createBooking(UUID eventId) throws BadRequestException {
        EventEntity event = findEventById(eventId);
        UserEntity user = UserContextManager.getUserFromContext();
        BookingEntity newBooking = bookingMapper.mapToNewEntity(user, event);
        saveBooking(newBooking);
        return bookingMapper.mapToDto(newBooking);
    }

    @Override
    public Booking cancelBooking(UUID bookingId) {
        BookingEntity booking = findBookingById(bookingId);
        bookingRepository.delete(booking);
        booking.setStatus(BookingStatus.CANCELED);
        return bookingMapper.mapToDto(booking);
    }

    @Override
    public List<User> retrieveGuestsOnEvent(UUID eventId) {
        EventEntity event = findEventById(eventId);
        List<UserEntity> rawGuests = bookingRepository.findGuestsOnEvent(event);
        return rawGuests.stream().map(userMapper::mapToDto).toList();
    }

    @Override
    public List<Booking> retrieveAllBookings() {
        UserEntity user = UserContextManager.getUserFromContext();
        List<BookingEntity> bookingEntities = bookingRepository.findAllByOwner(user);
        return bookingEntities.stream().map(bookingMapper::mapToDto).toList();
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
    private void saveBooking(BookingEntity booking) throws BadRequestException {
        try {
            bookingRepository.save(booking);
        } catch (Exception ex) {
            throw new BadRequestException("Вы можете создать только одну бронь на мероприятие");
        }
    }

}
