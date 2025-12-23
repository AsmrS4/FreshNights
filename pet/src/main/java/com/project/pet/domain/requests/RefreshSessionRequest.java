package com.project.pet.domain.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshSessionRequest {
    @NotBlank(message = "Токен обязателен к заполнению")
    private String refresh;
}
