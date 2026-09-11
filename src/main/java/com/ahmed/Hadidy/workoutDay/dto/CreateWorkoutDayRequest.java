package com.ahmed.Hadidy.workoutDay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateWorkoutDayRequest {

    @NotBlank
    private String name;

    @PositiveOrZero
    private Integer totalExercises = 0;

    @PositiveOrZero
    private Integer totalRepeat = 0;

    @PositiveOrZero
    private Double expectedTime = 0.0;

    private String description;

    private String image;

}
