package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.WorkoutDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutDayRepository extends JpaRepository<WorkoutDay, Long> {
    List<WorkoutDay> findAllByWorkoutPlanId(Long workoutPlanId);

    Optional<WorkoutDay> findByIdAndWorkoutPlanId(Long id, Long workoutPlanId);
}
