package com.ahmed.Hadidy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class WorkoutDay {

    private Long id ;

    private String name ;

    private int totalExercises;

    private int totalTimes ;

    private double expectedTime ;

    private String description ;

    private String image;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

}
