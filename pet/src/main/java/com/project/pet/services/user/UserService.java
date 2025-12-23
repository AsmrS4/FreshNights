package com.project.pet.services.user;

import com.project.pet.domain.models.User;
import com.project.pet.domain.requests.ChangePasswordRequest;
import com.project.pet.domain.requests.UpdateProfileRequest;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User retrieveUserProfile();
    User updateUserProfile(UpdateProfileRequest request) throws BadRequestException;
    List<User> retrieveAllUsers();
    List<User> retrieveAllManagers();
    boolean changeUserBlockStatus(UUID userId, boolean status);
    boolean changeUserPassword(ChangePasswordRequest request) throws BadRequestException;
}
