package com.ahmed.Hadidy.workoutPlan.dto;

import com.ahmed.Hadidy.workoutPlan.entity.WorkoutPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutPlanResponse {

    private Long id;

    private String name;

    private String description;

    private String picture;

    public WorkoutPlanResponse(WorkoutPlan w) {
        this.id = w.getId();
        this.name = w.getName();
        this.description = w.getDescription();
        this.picture = w.getPicture();
    }

}
