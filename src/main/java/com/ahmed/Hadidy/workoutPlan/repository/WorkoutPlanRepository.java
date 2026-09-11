package com.ahmed.Hadidy.workoutPlan.repository;

import com.ahmed.Hadidy.workoutPlan.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findAllByProfileId(Long userId);

    Optional<WorkoutPlan> findByIdAndProfileId(Long id, Long profileId);

}
