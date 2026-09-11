package com.ahmed.Hadidy.exercise.dto;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseRequest {

    private String name;

    private String description ;

    @PositiveOrZero
    private Integer repeat ;

    @PositiveOrZero
    private Integer sets ;

    private String picture;

}
