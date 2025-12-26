package com.project.pet.dao;

import com.project.pet.domain.entities.BookingEntity;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    Optional<BookingEntity> findBookingById(UUID bookingId);
    Optional<BookingEntity> findBookingByOwnerAndEvent(UserEntity owner, EventEntity event);
    List<BookingEntity> findAllByOwner(UserEntity owner);
    List<UserEntity> findGuestsOnEvent(EventEntity event);
}
