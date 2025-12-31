package com.project.pet.helpers.mappers;

import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.enums.UserRole;
import com.project.pet.domain.models.User;
import com.project.pet.domain.models.UserShort;
import com.project.pet.domain.requests.CreateAccountRequest;
import com.project.pet.domain.requests.UpdateProfileRequest;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Setter
public class UserMapper {
    private PasswordEncoder passwordEncoder;
    public UserEntity mapToNewUser(CreateAccountRequest request) {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setBlocked(false);
        newUser.setRole(UserRole.CLIENT);
        newUser.setHashPassword(passwordEncoder.encode(request.getPassword()));

        return newUser;
    }
    public User mapToDto(UserEntity userEntity) {
        User userDto = new User();
        userDto.setId(userEntity.getId());
        userDto.setImage(userEntity.getImage());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setRole(userEntity.getRole());
        userDto.setEmail(userEntity.getEmail());
        userDto.setAccountCreateTime(userEntity.getAccountCreateTime());

        return userDto;
    }
    public UserEntity mergeToEntity(UserEntity userEntity, UpdateProfileRequest request) {
        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setImage(request.getImage());

        return  userEntity;
    }
    public UserShort mapToShort(UserEntity user) {
        UserShort userShort = new UserShort();
        userShort.setId(user.getId());
        userShort.setFirstName(user.getFirstName());
        userShort.setLastName(user.getLastName());
        userShort.setImage(user.getImage());
        return userShort;
    }
}
