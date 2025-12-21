package com.project.pet.dao;

import com.project.pet.domain.entities.TokenEntity;
import com.project.pet.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByPayload(String payload);
    Optional<TokenEntity> findByOwner(UserEntity owner);
}
