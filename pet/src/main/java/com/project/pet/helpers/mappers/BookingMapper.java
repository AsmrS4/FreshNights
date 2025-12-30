package com.project.pet.helpers.mappers;

import com.project.pet.domain.entities.BookingEntity;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.enums.BookingStatus;
import com.project.pet.domain.models.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    public Booking mapToDto(BookingEntity booking) {
        Booking bookingDto = new Booking();
        bookingDto.setId(booking.getId());
        bookingDto.setOwner(
                userMapper.mapToShort(booking.getOwner())
        );
        bookingDto.setEvent(
                eventMapper.mapToShort(booking.getEvent())
        );
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setCreateTime(booking.getCreateTime());
        return bookingDto;
    }

    public BookingEntity mapToNewEntity(UserEntity owner, EventEntity event) {
        BookingEntity newBooking = new BookingEntity();
        newBooking.setOwner(owner);
        newBooking.setEvent(event);
        newBooking.setStatus(BookingStatus.ACTIVE);
        return newBooking;
    }
}
