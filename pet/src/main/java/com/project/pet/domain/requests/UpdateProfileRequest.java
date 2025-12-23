package com.project.pet.domain.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    @Nullable
    @Size(min = 3, max = 100, message = "Допустимая длина для имени от 3 до 100 символов")
    private String firstName;
    @Nullable
    @Size(min = 3, max = 100, message = "Допустимая длина для фамилии от 3 до 100 символов")
    private String lastName;
    @Nullable
    private String image;
    @NotBlank(message = "Email обязателен к заполению")
    @Email(message = "Невалидный формат email")
    private String email;
}
