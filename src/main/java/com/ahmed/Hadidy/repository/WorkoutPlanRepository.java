package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findAllByProfileId(Long userId);

    Optional<WorkoutPlan> findByIdAndProfileId(Long id, Long profileId);

}
