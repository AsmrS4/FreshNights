package com.project.pet.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Пароль обязателен к заполению")
    @Size(min = 8, max = 20, message = "Длина пароля от 8 до 20 символов")
    private String currentPassword;
    @NotBlank(message = "Новый пароль обязателен к заполению")
    @Size(min = 8, max = 20, message = "Длина пароля от 8 до 20 символов")
    private String newPassword;
}
