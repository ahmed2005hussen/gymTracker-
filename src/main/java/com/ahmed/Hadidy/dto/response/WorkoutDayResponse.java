package com.ahmed.Hadidy.dto.response;

import com.ahmed.Hadidy.entity.WorkoutDay;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutDayResponse {

    private Long id;

    private String name;

    private Integer totalExercises;

    private Integer totalRepeat;

    private Double expectedTime;

    private String description;

    private String image;

    private Long workoutPlanId;

    public WorkoutDayResponse(WorkoutDay d) {

        this.id = d.getId();
        this.name = d.getName();
        this.description = d.getDescription();
        this.totalExercises = d.getTotalExercises();
        this.totalRepeat = d.getTotalRepeat();
        this.expectedTime = d.getExpectedTime();
        this.image = d.getImage();
        this.workoutPlanId = d.getWorkoutPlan().getId();
    }

}
