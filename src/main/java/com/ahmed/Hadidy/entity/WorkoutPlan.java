package com.ahmed.Hadidy.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class WorkoutPlan {

    private Long id ;

    private String name ;

    private String description ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate generateDate ;

    @OneToMany(mappedBy = "workoutPlan")
    private Set<WorkoutDay> workoutDays = new HashSet<>();


    private boolean isPublic ;


}
