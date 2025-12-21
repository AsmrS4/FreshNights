package com.project.pet.helpers.mappers;

import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.enums.UserRole;
import com.project.pet.domain.requests.CreateAccountRequest;
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
       // newUser.setHashPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setHashPassword(request.getPassword());
        return newUser;
    }
}
