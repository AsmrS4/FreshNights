package com.project.pet.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email обязателен к заполению")
    @Email(message = "Невалидный формат email")
    private String email;
    @NotBlank(message = "Пароль обязателен к заполению")
    @Size(min = 8, max = 20, message = "Длина пароля от 8 до 20 символов")
    private String password;
}
