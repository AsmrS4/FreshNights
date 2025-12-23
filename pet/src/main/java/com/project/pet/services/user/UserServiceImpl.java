package com.project.pet.services.user;

import com.project.pet.dao.UserRepository;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.models.User;
import com.project.pet.domain.requests.ChangePasswordRequest;
import com.project.pet.domain.requests.UpdateProfileRequest;
import com.project.pet.helpers.encoder.PasswordEncoderManager;
import com.project.pet.helpers.mappers.UserMapper;
import com.project.pet.helpers.user.UserContextManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User retrieveUserProfile() {
        UserEntity currentUser = UserContextManager.getUserFromContext();
        return userMapper.mapToDto(currentUser);
    }

    @Override
    public User updateUserProfile(UpdateProfileRequest request) throws BadRequestException {
        UserEntity currentUser = UserContextManager.getUserFromContext();
        validateUserEmail(request.getEmail(), currentUser.getEmail());
        userMapper.mergeToEntity(currentUser, request);
        return userMapper.mapToDto(saveUser(currentUser));
    }

    @Override
    public List<User> retrieveAllUsers() {
        return List.of();
    }

    @Override
    public List<User> retrieveAllManagers() {
        return List.of();
    }

    @Override
    public boolean changeUserBlockStatus(UUID userId, boolean status) {
        UserEntity user = findUserById(userId);
        user.setBlocked(status);
        saveUser(user);
        return true;
    }

    @Override
    public boolean changeUserPassword(ChangePasswordRequest request) throws BadRequestException {
        UserEntity user = UserContextManager.getUserFromContext();
        if(!PasswordEncoderManager.isPasswordCorrect(request.getCurrentPassword(), user.getHashPassword())) {
            throw new BadRequestException("Некорректный пароль");
        }
        if(request.getCurrentPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("Новый пароль не должен совпадать со старым");
        }
        user.setHashPassword(PasswordEncoderManager.encodePassword(request.getNewPassword()));
        saveUser(user);
        return true;
    }

    private void validateUserEmail(String emailFromRequest, String currentUserEmail) throws BadRequestException {
        if(userRepository.existsByEmail(emailFromRequest)) {
            if(!emailFromRequest.equals(currentUserEmail)) {
                throw new BadRequestException("Логин занят");
            }
        }
    }

    private UserEntity findUserById(UUID userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователь с id: %s не найден", userId))
        );
    }

    private UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
