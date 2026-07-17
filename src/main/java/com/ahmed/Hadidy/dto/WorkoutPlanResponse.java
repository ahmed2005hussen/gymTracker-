package com.ahmed.Hadidy.dto;

import com.ahmed.Hadidy.entity.WorkoutDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanResponse {


    private String name ;

    private String description ;

    private String picture ;

    private List<WorkoutDay> workoutDays = null;


}
