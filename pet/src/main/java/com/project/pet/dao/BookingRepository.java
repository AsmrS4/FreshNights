package com.project.pet.dao;

import com.project.pet.domain.entities.BookingEntity;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    Optional<BookingEntity> findBookingById(UUID bookingId);
    Optional<BookingEntity> findBookingByOwnerAndEvent(UserEntity owner, EventEntity event);
    List<BookingEntity> findAllByOwner(UserEntity owner);
    @Query("SELECT be.owner FROM BookingEntity be WHERE be.event = :event AND be.status = 0")
    List<UserEntity> findGuestsOnEvent(@Param("event") EventEntity event);
}
