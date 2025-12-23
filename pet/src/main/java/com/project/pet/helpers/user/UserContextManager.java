package com.project.pet.helpers.user;

import com.project.pet.dao.UserRepository;
import com.project.pet.domain.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component()
@Scope(scopeName = "singleton")
@Slf4j
public class UserContextManager {
    @Autowired
    private static UserRepository userRepository;
    public UserContextManager(@Autowired UserRepository userRepository) {
        UserContextManager.userRepository = userRepository;
    }
    public static UUID getUserIdFromContext() {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        } catch (Exception ex) {
            log.error("REVEIVED ERROR: {}", ex);
        }
        return null;
    }
    public static UserEntity getUserFromContext() {
        UUID userId = getUserIdFromContext();
        return userRepository.findUserById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("Пользователь не найден")
        );
    }
}
