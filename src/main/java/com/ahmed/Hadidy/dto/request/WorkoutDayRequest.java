package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutDayRequest {

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
