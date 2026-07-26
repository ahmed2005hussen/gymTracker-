package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MealRequest {

    private String name;

    private String recipe;

    private String photo;

    @PositiveOrZero
    private Double calories;

    @PositiveOrZero
    private Double protein;

    @PositiveOrZero
    private Double carbs;

    @PositiveOrZero
    private Double fats;

    private String time;

}
