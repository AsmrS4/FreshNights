package com.project.pet.dao;

import com.project.pet.domain.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    Optional<EventEntity> findEventById(UUID eventId);
    @Query("SELECT * FROM EventEntity ee WHERE ee.status = 0")
    List<EventEntity> findAllActive();
}
