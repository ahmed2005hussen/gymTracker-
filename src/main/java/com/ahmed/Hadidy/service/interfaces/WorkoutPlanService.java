package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.entity.WorkoutPlan;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanService {

    WorkoutPlan save(WorkoutPlan workoutPlan);

    List<WorkoutPlan> findAll();

    Optional<WorkoutPlan> findById(Long id);

    void deleteById(Long id );

    WorkoutPlan update(Long id, WorkoutPlan workoutPlan);

    List<WorkoutPlan> findByUserId(Long userId);

}
