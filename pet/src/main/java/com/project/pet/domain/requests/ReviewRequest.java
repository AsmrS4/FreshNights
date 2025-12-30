package com.project.pet.domain.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotBlank(message = "Комментарий обязателен к заполнению")
    @Min(value = 10, message = "Минимальный размер комментария - 10 символов")
    @Max(value = 2000, message = "Максимальная длина комментария - 2000 символов")
    private String text;
    @NotBlank(message = "Укажите оценку")
    @Min(value = 1, message = "Минимальное значение 1")
    @Max(value = 10, message = "Максимальное значение 10")
    private int rating;
}
