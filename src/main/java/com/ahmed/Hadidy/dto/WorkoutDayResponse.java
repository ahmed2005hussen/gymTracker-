package com.ahmed.Hadidy.dto;

import com.ahmed.Hadidy.entity.Exercise;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDayResponse {


    private String name ;

    private int totalExercises;

    private int totalRepeat;

    private double expectedTime ;

    private String description ;

    private String image;

    private Long workoutPlanId;

    private Set<Exercise> exercises ;


}
