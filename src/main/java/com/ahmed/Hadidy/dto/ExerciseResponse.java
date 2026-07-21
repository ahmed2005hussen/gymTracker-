package com.ahmed.Hadidy.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponse {

    private String name;

    private String description ;

    private Integer repeat ;

    private Integer sets ;

    private String picture;

    private Long workoutDayId;

}
