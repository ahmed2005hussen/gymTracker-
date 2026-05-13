package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.WorkoutDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutDayRepository extends JpaRepository<WorkoutDay,Long> {
    List<WorkoutDay> findByWorkoutPlanId(Long workoutPlanId);
}
