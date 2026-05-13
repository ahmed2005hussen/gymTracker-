package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.entity.WorkoutDay;

import java.util.List;
import java.util.Optional;

public interface WorkoutDayService {

    WorkoutDay save(WorkoutDay workoutDay);

    List<WorkoutDay> findAll();

    Optional<WorkoutDay> findById(Long id);

    void deleteById(Long id );

    WorkoutDay update(Long id, WorkoutDay workoutDay);

    List<WorkoutDay> findByWorkoutPlanId(Long workoutPlanId);

}
