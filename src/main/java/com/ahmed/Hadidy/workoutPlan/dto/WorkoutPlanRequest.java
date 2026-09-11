package com.ahmed.Hadidy.workoutPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanRequest {

    private String name;

    private String description;

    private String picture;


}
