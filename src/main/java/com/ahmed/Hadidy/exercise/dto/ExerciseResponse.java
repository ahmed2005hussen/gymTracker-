package com.ahmed.Hadidy.exercise.dto;


import com.ahmed.Hadidy.exercise.entity.Exercise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseResponse {

    private Long id;

    private String name;

    private String description;

    private Integer repeat;

    private Integer sets;

    private String picture;

    public ExerciseResponse(Exercise exercise) {
        this.name = exercise.getName();
        this.description = exercise.getDescription();
        this.id = exercise.getId();
        this.picture = exercise.getPicture();
        this.repeat = exercise.getRepeat();
        this.sets = exercise.getSets();
    }

}
