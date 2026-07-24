package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DietPlanRepository extends JpaRepository<DietPlan,Long> {
    List<DietPlan> findAllByProfileId(Long profileId);
    Optional<DietPlan> findByIdAndProfileId(Long id , Long profileId);
}
