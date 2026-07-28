package com.ahmed.Hadidy.dto.request;

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
    private Integer totalExercises;

    @PositiveOrZero
    private Integer totalRepeat;

    @PositiveOrZero
    private Double expectedTime;

    private String description;

    private String image;

}
