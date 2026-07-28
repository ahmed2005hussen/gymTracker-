package com.ahmed.Hadidy.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {

    @NotBlank
    private String name;

    private String description;

    @PositiveOrZero
    private Integer repeat = 0;

    @PositiveOrZero
    private Integer sets = 0;

    private String picture;


}
